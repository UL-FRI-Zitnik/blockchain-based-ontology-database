import io.ipfs.api.IPFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: check if rbox is required, handle rBox in model
// TODO: prepare .ru sparql queries/showcase for toy ontology and dbpedia
// TODO: demonstrate consistency checking, improve efficiency of checking for consistency for large datasets

// TODO: remove duplicate code...
// TODO: Improve error handling
public class Demo {
    private static final Logger log = LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) {
        ConfigLoader configLoader = new ConfigLoader("src/main/java/config.yaml");
        ArrayList<String> loadFromFiles = (ArrayList<String>)configLoader.getOntology().get("loadFromFiles");
        ArrayList<HashMap<String, String>> dumpToFiles = (ArrayList<HashMap<String, String>>)configLoader.getOntology().get("dumpToFiles");
        String IPFSNodeAddress = (String)configLoader.getIPFS().get("nodeAddress");
        String ethereumNodeAddress = (String)configLoader.getEthereum().get("nodeAddress");
        ArrayList<String> SPARQLQueries = (ArrayList<String>)configLoader.getOntology().get("SPARQLQueries");

        IPFSHelpers ipfsHelpers = new IPFSHelpers(new IPFS(IPFSNodeAddress));

        Web3Helpers web3Helpers = new Web3Helpers(ethereumNodeAddress, configLoader.isDevelopment());
        web3Helpers.loadCredentials(configLoader);

        String aBoxFullPath = getABoxFilePath(dumpToFiles);
        String tBoxFullPath = getTBoxFilePath(dumpToFiles);

        // Separate schema and data
        OntologyHelpers ontologyHelpers = localDataSchemaSplitAndSaveToFile(loadFromFiles, aBoxFullPath, tBoxFullPath);
        // Upload schema and data files to IPFS
        String aBoxCID = ipfsHelpers.uploadLocalFile(aBoxFullPath).toString();
        String tBoxCID = ipfsHelpers.uploadLocalFile(tBoxFullPath).toString();
        log.info("[IPFS upload] aBox CID: "+aBoxCID + " tBox CID: "+tBoxCID);

        // Store schema and data CIDs to Ethereum
        String contractAddress = storeDataOnEthereumAndGetContractAddress(web3Helpers, tBoxCID, aBoxCID);
        // Retrieve schema and data pointers from Ethereum
        String[] ontology = retrieveIPFSHashesForSchemaAndDataFromEthereum(web3Helpers, contractAddress);
        tBoxCID = ontology[0];
        aBoxCID = ontology[1];

        log.info("[ETH retrieve] aBox CID: "+aBoxCID + " tBox CID: "+tBoxCID);
        log.info("[IPFS download and write to files] ");
        // Download data from IPFS
        downloadDataDownstream(ipfsHelpers, aBoxCID, tBoxCID, aBoxFullPath, tBoxFullPath);
        log.info("[Load files to triplestore and run SPARQL] ");
        // Load the schema and data files into the Apache Jena
        loadABoxToBoxToJenaAndPerformSPARQLOperations(aBoxFullPath, tBoxFullPath, SPARQLQueries);
    }

    public static String storeDataOnEthereumAndGetContractAddress(Web3Helpers web3Helpers, String tBoxCID, String aBoxCID) {
        String contractAddress = web3Helpers.deployStorageContract();
        TransactionReceipt storeTransactionReceipt = web3Helpers.loadStorageContractAndCallStoreMethod(contractAddress, tBoxCID, aBoxCID);
        String storeTransactionHash = storeTransactionReceipt.getTransactionHash();
        log.info("Store data transaction: "+storeTransactionHash);
        return contractAddress;
    }

    public static String[] retrieveIPFSHashesForSchemaAndDataFromEthereum(Web3Helpers web3Helpers, String contractAddress) {
        String[] contractCIDs = web3Helpers.loadStorageContractAndCallRetrieveMethod(contractAddress);
        return contractCIDs;
    }

    public static OntologyHelpers localDataSchemaSplitAndSaveToFile(ArrayList<String> loadFromFiles, String aBoxFullPath, String tBoxFullPath) {
        OntologyHelpers ontologyHelpers = new OntologyHelpers(loadFromFiles);
        // Save to file and upload to IPFS
        ontologyHelpers.saveABoxAxiomsToFile(aBoxFullPath);
        ontologyHelpers.saveTBoxAxiomsToFile(tBoxFullPath);
        return ontologyHelpers;
    }

    public static String getABoxFilePath(ArrayList<HashMap<String, String>> dumpToFiles) {
        for (HashMap<String, String> el : dumpToFiles) {
            if (el.containsKey("abox")) {
                return el.get("abox");
            }
        }

        log.error("Dump files must contain rbox, abox, tbox files");
        return null;
    }

    public static String getTBoxFilePath(ArrayList<HashMap<String, String>> dumpToFiles) {
        for (HashMap<String, String> el : dumpToFiles) {
            if (el.containsKey("tbox")) {
                return el.get("tbox");
            }
        }
        log.error("Dump files must contain rbox, abox, tbox files");
        return null;
    }

    public static void downloadDataDownstream(IPFSHelpers ipfsHelpers, String aBoxFileHash, String tBoxFileHash, String aBoxFullPath, String tBoxFullPath) {
        ipfsHelpers.retrieveFileAndSaveItToLocalSystem(aBoxFileHash, aBoxFullPath);
        ipfsHelpers.retrieveFileAndSaveItToLocalSystem(tBoxFileHash, tBoxFullPath);
    }

    public static void loadABoxToBoxToJenaAndPerformSPARQLOperations(String aBoxFullPath, String tBoxFullPath, ArrayList<String> SPARQLQueries) {


        JenaHelpers jenaHelpers = new JenaHelpers(tBoxFullPath, aBoxFullPath);
//        JenaHelpers jenaHelpers = new JenaHelpers(inputDBPediaTBoxFullPath, inputDBPediaABoxFullPath);

        for(String query : SPARQLQueries) {
            log.info("[Executing SPARQL from file] "+query);
            jenaHelpers.executeSPARQL(query);
//            jenaHelpers.printDatasetToStandardOutput();
        }
    }

}
