package ma.enova.rth.common.ddd.process;

import org.springframework.beans.BeanUtils;

public abstract class AbstractProcessImpl<T extends AbstractProcessInput, K> {


    public Result execute(T input, K output) {
        Result<T, K> result = new Result();
        result.setInput(input);
        result.setOutput(output);
        BeanUtils.copyProperties(input, output);
        init(input, output);
        validate(input, output, result);
        if (result.hasNoError()) {
            run(input, output, result);
        } else {
            cleanUp(input, output, result);
        }
        return result;
    }

    public void cleanUp(T input, K output, Result<T, K> result) {
    }

    public void init(T input, K output) {

    }


    public abstract void validate(T input, K output, Result<T, K> result);

    public abstract void run(T input, K output, Result<T, K> result);

}
