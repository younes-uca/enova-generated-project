package ma.enova.rth.common.ddd.process;

public interface AbstractProcess<T extends AbstractProcessInput, K> {
    Result execute(T input, K output);
}
