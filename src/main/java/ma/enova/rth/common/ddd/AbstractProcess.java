package ma.enova.rth.common.ddd;

public interface AbstractProcess<T extends AbstractProcessInput, K> {
    public Result execute(T input, K output);
}
