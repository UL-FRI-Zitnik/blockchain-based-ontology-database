import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.NTriplesDocumentFormat;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.Imports;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class OntologyHelpers {
    private OWLOntology ontology;

    public OntologyHelpers(ArrayList<String> filesToLoad) {
        if (filesToLoad.size() < 1) {
            System.out.println("Must have at least 1 input ontology");
            return;
        }
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        for (String fileName : filesToLoad) {
            try {
                manager.loadOntologyFromOntologyDocument(new File(fileName));
            } catch (OWLOntologyCreationException e) {
                e.printStackTrace();
            }
        }
        Set<OWLOntology> ontologies = manager.getOntologies();

        Iterator iter = ontologies.iterator();
        if (iter.hasNext()) {
            ontology = (OWLOntology)iter.next();
        }


//        StructuralReasonerFactory factory = new StructuralReasonerFactory();
//        OWLReasoner reasoner = factory.createReasoner(ontology);
//        engine = QueryEngine.create(manager, reasoner, true);
    }

    public OWLDocumentFormat chooseCorrectFormat(String fileName) {
        if (fileName.contains("ttl")) {
            return new TurtleDocumentFormat();
        }
        if (fileName.contains("nt")) {
            return new NTriplesDocumentFormat();
        }
        System.out.println("chooseCorrectFormat could not guess format from file extension");
        return null;
    }

    public void saveTBoxAxiomsToFile(String fileName) {
        Set<OWLAxiom> tBoxAxioms = ontology.getTBoxAxioms(Imports.INCLUDED);
        OWLOntologyManager tBoxManager = OWLManager.createOWLOntologyManager();
        OWLOntology tBoxOntology = null;
        try {
            tBoxOntology = tBoxManager.createOntology();
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        tBoxManager.addAxioms(tBoxOntology, tBoxAxioms);
        saveOntologyToFile(fileName, tBoxOntology, chooseCorrectFormat(fileName));
    }

    public void saveABoxAxiomsToFile(String fileName) {
        Set<OWLAxiom> aBoxAxioms = ontology.getABoxAxioms(Imports.INCLUDED);
        OWLOntologyManager tBoxManager = OWLManager.createOWLOntologyManager();
        OWLOntology aBoxOntology = null;
        try {
            aBoxOntology = tBoxManager.createOntology();
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        tBoxManager.addAxioms(aBoxOntology, aBoxAxioms);
        saveOntologyToFile(fileName, aBoxOntology, chooseCorrectFormat(fileName));
    }

    public void saveRBoxAxiomsToFile(String fileName) {
        Set<OWLAxiom> rBoxAxioms = ontology.getRBoxAxioms(Imports.INCLUDED);
        OWLOntologyManager rBoxManager = OWLManager.createOWLOntologyManager();
        OWLOntology rBoxOntology = null;
        try {
            rBoxOntology = rBoxManager.createOntology();
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        rBoxManager.addAxioms(rBoxOntology, rBoxAxioms);
        saveOntologyToFile(fileName, rBoxOntology, chooseCorrectFormat(fileName));
    }

    public void saveOntologyToFile(String outputFile, OWLOntology owlOntology, OWLDocumentFormat owlDocumentFormat) {
        try {
            File outputOntology = new File(outputFile);
            owlOntology.saveOntology(owlDocumentFormat, IRI.create(outputOntology.toURI()));
        } catch (OWLOntologyStorageException e) {
            e.printStackTrace();
        }
    }
}
