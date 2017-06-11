package Javascript;

import ResourceObjects.Artist;
import ResourceObjects.Genre;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

public class JavascriptExamples {

    public static void run() throws ScriptException, NoSuchMethodException, FileNotFoundException {

        //Define engine and javascript file
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        String filename = System.getProperty("user.dir") + "/src/Javascript/main.js";
        scriptEngine.eval(new FileReader(filename));
        Invocable invocable = (Invocable) scriptEngine;

        //Invoke method and return result
        Object result = invocable.invokeFunction(
                "testFunction",
                new Artist("Jack", Genre.METAL, LocalDate.of(1993, 5, 3))
        );
        System.out.println(result);
    }
}
