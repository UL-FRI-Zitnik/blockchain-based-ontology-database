# Master's thesis 
Title: Integracija verige blokov in tehnologij semantičnega spleta

Title (English): Integration of blockchain and semantic web technologies

# Get up and running

### 
#### Install [solidity compiler](https://docs.soliditylang.org/en/v0.8.0/installing-solidity.html)
#### Install [web3j](http://docs.web3j.io/latest/quickstart/)
#### Generate smart contract wrappers
```bash
cd src/main/java;
./generateWrappers.sh
```

### Run Ethereum node with Ganache (local node)
- cli
```bash
npm install -g ganache-cli
ganache-cli -h 0.0.0.0 -d -m "example onion where village dignity affair lady inject spray car bomb two"
# set ethereumNodeAddress to "http://localhost:8545"
```
- gui
[Ganache](https://www.trufflesuite.com/ganache)

### Run Ethereum node on Infura (hosted node)
In order to connect to other Ethereum node change ethereum/nodeAddress in config.yaml

### Run IPFS node
[IPFS](https://ipfs.io/#install)

### Run program
set heap space big enough to input ontology files e.g. -ea -Xmx2048m

### Run program on the dataset from the article Blockchain-based transaction manager for ontology databases
We have run three configurations sequentially (A, B and C), (A, B and C), ... several times. 
Three run configurations in XML format used for scenarios A,B,C are available in folder `.idea/runConfigurations`
Jetbrains editor will load configurations when opening the project.
For each run of the configuration csv file with measurements of the run gets created which were used for the analysis of results. 
 