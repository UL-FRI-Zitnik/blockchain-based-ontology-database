package contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Storage extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610403806100206000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806354f194a41461005157806372090cc91461006f578063f6480d8b14610077578063f96285501461008c575b600080fd5b610059610094565b6040516100669190610329565b60405180910390f35b610059610126565b61008a6100853660046102a5565b610135565b005b610059610176565b6060600180546100a39061037c565b80601f01602080910402602001604051908101604052809291908181526020018280546100cf9061037c565b801561011c5780601f106100f15761010080835404028352916020019161011c565b820191906000526020600020905b8154815290600101906020018083116100ff57829003601f168201915b5050505050905090565b6060600280546100a39061037c565b8251610148906000906020860190610185565b50815161015c906001906020850190610185565b508051610170906002906020840190610185565b50505050565b6060600080546100a39061037c565b8280546101919061037c565b90600052602060002090601f0160209004810192826101b357600085556101f9565b82601f106101cc57805160ff19168380011785556101f9565b828001600101855582156101f9579182015b828111156101f95782518255916020019190600101906101de565b50610205929150610209565b5090565b5b80821115610205576000815560010161020a565b600082601f83011261022e578081fd5b813567ffffffffffffffff80821115610249576102496103b7565b604051601f8301601f19908116603f01168101908282118183101715610271576102716103b7565b81604052838152866020858801011115610289578485fd5b8360208701602083013792830160200193909352509392505050565b6000806000606084860312156102b9578283fd5b833567ffffffffffffffff808211156102d0578485fd5b6102dc8783880161021e565b945060208601359150808211156102f1578384fd5b6102fd8783880161021e565b93506040860135915080821115610312578283fd5b5061031f8682870161021e565b9150509250925092565b6000602080835283518082850152825b8181101561035557858101830151858201604001528201610339565b818111156103665783604083870101525b50601f01601f1916929092016040019392505050565b600181811c9082168061039057607f821691505b602082108114156103b157634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fdfea2646970667358221220390be44050ede885170a3b86809c8e002b080167c881a8b368e2abb9a847eeb164736f6c63430008040033";

    public static final String FUNC_GETABOX = "getABox";

    public static final String FUNC_GETRBOX = "getRBox";

    public static final String FUNC_GETTBOX = "getTBox";

    public static final String FUNC_STORETBOXABOX = "storeTBoxABox";

    @Deprecated
    protected Storage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Storage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Storage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Storage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getABox() {
        final Function function = new Function(FUNC_GETABOX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getRBox() {
        final Function function = new Function(FUNC_GETRBOX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getTBox() {
        final Function function = new Function(FUNC_GETTBOX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> storeTBoxABox(String _tBox, String _aBox, String _rBox) {
        final Function function = new Function(
                FUNC_STORETBOXABOX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_tBox), 
                new org.web3j.abi.datatypes.Utf8String(_aBox), 
                new org.web3j.abi.datatypes.Utf8String(_rBox)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Storage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Storage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Storage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Storage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Storage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Storage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Storage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Storage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Storage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Storage.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Storage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Storage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Storage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Storage.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Storage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Storage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
