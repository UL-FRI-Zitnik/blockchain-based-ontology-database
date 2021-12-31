# Integration of blockchain and semantic web technologies

This repository contains source code for the author's (Domen Gašperlin) master thesis, titled [Integration of blockchain and semantic web technologies](https://repozitorij.uni-lj.si/IzpisGradiva.php?id=133281).

His work was further extended with additional analyses and evaluation for a journal paper (*TO APPEAR*).

# Project setup

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
We have run three configurations (A, B and C) in that order several times. After C we started with A again.
Three run configurations in XML format used for scenarios A,B,C are available in folder `.idea/runConfigurations`
Jetbrains editor will load configurations when opening the project.
For each run of the configuration csv file with measurements of the run gets created which were used for the analysis of results. 
 
