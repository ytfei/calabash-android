package sh.calaba.instrumentationbackend.actions.text;


import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;


public class EnterTextByIndex implements Action {

    /**
     * args[0] text content
     * args[1] edit text index
     * args[2] optional, whether add random content to the text
     *
     * @param args
     * @return
     */
    @Override
    public Result execute(String... args) {
        boolean random = false;
        if (args.length >= 3)
            random = Boolean.parseBoolean(args[2]);

        InstrumentationBackend.solo.enterText(Integer.parseInt(args[1]) - 1, rand(args[0], random));
        return Result.successResult();
    }

    private String rand(String arg, boolean random) {
        if (!random)
            return arg;

        return arg + " -- " + System.currentTimeMillis();
    }

    @Override
    public String key() {
        return "enter_text_into_numbered_field";
    }

}
