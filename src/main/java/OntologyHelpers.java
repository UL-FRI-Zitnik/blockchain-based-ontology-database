import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.NTriplesDocumentFormat;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.Imports;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class OntologyHelpers {
    private Set<OWLOntology> ontologies;
    private static final Logger log = Logger.getLogger(OntologyHelpers.class.getName());
    public OntologyHelpers(ArrayList<String> filesToLoad) {
        if (filesToLoad.size() < 1) {
            log.severe("Must have at least 1 input ontology");
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
        this.ontologies = manager.getOntologies();

//        StructuralReasonerFactory factory = new StructuralReasonerFactory();
//        OWLReasoner reasoner = factory.createReasoner(ontology);
//        engine = QueryEngine.create(manager, reasoner, true);
    }

    public static Boolean convertOntologyFormat(String ontologyLocation, String outputFile, OWLDocumentFormat owlDocumentFormat) {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology owlOntology = null;
        try {
            owlOntology = manager.loadOntologyFromOntologyDocument(new File(ontologyLocation));
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
            return false;
        }

        try {
            File outputOntology = new File(outputFile);
            owlOntology.saveOntology(owlDocumentFormat, IRI.create(outputOntology.toURI()));
        } catch (OWLOntologyStorageException e) {
            e.printStackTrace();
        }
        return true;
    }

    public OWLDocumentFormat chooseCorrectFormat(String fileName) {
        if (fileName.contains("ttl")) {
            return new TurtleDocumentFormat();
        }
        if (fileName.contains("nt")) {
            return new NTriplesDocumentFormat();
        }
        log.severe("chooseCorrectFormat could not guess format from file extension");
        return null;
    }

    public void saveTBoxAxiomsToFile(String fileName) {
        Set<OWLAxiom> tBoxAxioms = new HashSet<>();
        for(OWLOntology ontology : ontologies) {
            tBoxAxioms.addAll(ontology.getTBoxAxioms(Imports.INCLUDED));
        }

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

    public void saveRBoxAxiomsToFile(String fileName) {
        Set<OWLAxiom> rBoxAxioms = new HashSet<>();
        for(OWLOntology ontology : ontologies) {
            rBoxAxioms.addAll(ontology.getRBoxAxioms(Imports.INCLUDED));
        }

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

    public void saveABoxAxiomsToFile(String fileName) {
        Set<OWLAxiom> aBoxAxioms = new HashSet<>();
        for(OWLOntology ontology : ontologies) {
            aBoxAxioms.addAll(ontology.getABoxAxioms(Imports.INCLUDED));
        }
        OWLOntologyManager aBoxManager = OWLManager.createOWLOntologyManager();
        OWLOntology aBoxOntology = null;
        try {
            aBoxOntology = aBoxManager.createOntology();
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        aBoxManager.addAxioms(aBoxOntology, aBoxAxioms);
        saveOntologyToFile(fileName, aBoxOntology, chooseCorrectFormat(fileName));
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
