import cs.technion.ac.il.sd.ExternalCompiler;
import cs.technion.ac.il.sd.app.Makefile;
import makefileImpl.MakefileImpl;

import java.io.File;

/**
 * Created by akravi on 5/12/2016.
 */
public class AcceptanceTest2 implements IAcceptanceTest{
    private final ExternalCompiler mock = injector.getInstance(ExternalCompiler.class);
    Makefile $ = new MakefileImpl(mock);

    @Override
    public void Run(String fileName) {
        $.processFile(new File(getClass().getResource(fileName).getFile()));
    }
}
