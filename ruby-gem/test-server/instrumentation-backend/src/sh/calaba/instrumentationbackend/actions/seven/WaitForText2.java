package sh.calaba.instrumentationbackend.actions.seven;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

/**
 * User: eyang
 * Date: 12/6/12
 * Time: 9:01 AM
 */
public class WaitForText2 implements Action {
    @Override
    public Result execute(String... args) {
        String expectedText;
        int timeout = 90 * 1000;
        int buttonNumber = 1;

        expectedText = args[0];
        if (args.length >= 2) {
            try {
                // the argument is in seconds but robotium takes milliseconds
                timeout = 1000 * Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                return new Result(false, "Invalid timeout supplied. Should be an integer.");
            }
        }
        buttonNumber = Integer.parseInt(args[args.length - 1]) - 1;


        boolean timedOut = !InstrumentationBackend.solo.waitForText(expectedText, 1, timeout);
        if (timedOut) {
            // the page doesn't appear, just return
            return new Result(true, "Time out while waiting for text:" + expectedText);
        } else {
            // the expected page appear, do the next:
            InstrumentationBackend.solo.clickOnButton(buttonNumber);
            return Result.successResult();
        }

    }

    @Override
    public String key() {
        return "wait_for_text_and_then_press_button";
    }
}
