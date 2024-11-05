import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ProcessorImpl implements Processor {
    @Override
    public String process(Callable<String> c) throws Exception {
        // implementation details
        return new String();
    }

    @Override
    public String process(Supplier<String> s) {
        // implementation details
        return new String();
    }
}


