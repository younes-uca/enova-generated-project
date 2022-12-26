package ma.enova.rth.zynerator.process;

public interface AbstractProcess<T extends AbstractProcessInput, K> {
    Result<T,K> execute(T input, K output);
}
