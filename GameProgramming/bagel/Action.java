package bagel;

/**
 * This class contains a function that is applied to a Sprite
 *   automatically over time.
 */
public class Action
{
    // define the format that all Action functions must have.
    //  function parameters must include: 
    //  * Sprite -- target of the action
    //  * delta Time -- the amount of time that has passed since last update (1/60)
    //  * total Time -- the amount of time passed since the Action started

    //  * return a boolean: true if the Action is finished.

    public interface Function
    {
        public boolean run(Sprite target, double deltaTime, double totalTime);
    }

    // the actual function instance applied to Sprite
    public Function function;
    // how long this Action has been running
    public double totalTime;

    /**
     * Creates an Action object, which contains a function applied to a Sprite
     *   automatically over time.
     *
     * @param f The function that will be applied to the Sprite.
     */
    public Action(Function f)
    {
        function = f;
    }

    /**
     * Apply the Action's function to a Sprite.
     *
     * @param target The Sprite the function is being applied to.
     * @param deltaTime How much time has passed since last update (1/60)
     * @return boolean, true if action has finished.
     */
    public boolean apply(Sprite target, double deltaTime)
    {
        // update the total amount of time that has passed
        totalTime += deltaTime;
        // run the function on a sprite
        return function.run(target, deltaTime, totalTime);
    }

    /**
     * To re-use an action, need to reset the total running time to 0.
     * (Resetting a sequence is slightly different... need to reset totalTime of multiple actions.)
     */
    public void reset()
    {
        totalTime = 0;
    }

    
    // methods to create Action objects
    // static methods, to more easily create Actions directly from class

    /**
     * Create an Action containing a function that automatically moves a Sprite
     *   over some amount of time.
     *
     * @param deltaX total amount to move in x-direction
     * @param deltaY total amount to move in y-direction
     * @param duration total amount of time Sprite will be moving
     * @return true if action is complete
     */
    public static Action moveBy(double deltaX, double deltaY, double duration)
    {
        Function func = new Function()
            {
                public boolean run(Sprite target, double deltaTime, double totalTime)
                {
                    target.moveBy( deltaX * deltaTime/duration,
                        deltaY * deltaTime/duration  );
                    // this action is finished, once it has been running (totalTime)
                    //   for the desired duration of time.
                    boolean finished = (totalTime >= duration);
                    return finished;
                }
            };
        return new Action(func);   
    }

    /**
     * Create an Action containing a function that automatically rotates a Sprite
     *   over some amount of time.
     *
     * @param deltaA total angle to rotate Sprite by
     * @param duration total amount of time rotation takes
     * @return Action
     */
    public static Action rotateBy(double deltaA, double duration)
    {
        Function func = new Function()
            {
                public boolean run(Sprite target, double deltaTime, double totalTime)
                {
                    target.rotateBy( deltaA * deltaTime/duration );
                    boolean finished = (totalTime >= duration);
                    return finished;
                }
            };
        return new Action(func);
    }

    /**
     * Perform a sequence of Actions, one after the other, instead of running all
     *   Actions at the same time (which is the default).
     *
     * @param actionArray an array of Actions
     * @return Action
     */
    public static Action sequence(Action[] actionArray)
    {
        return new Action(null)
        {
            public int actionIndex = 0;

            public Action()
            {
                function = new Function() 
                {
                    public boolean run(Sprite target, double deltaTime, double totalTime)
                    {
                        Action currentAction = actionArray[ actionIndex ];
                        boolean finished = currentAction.apply( target, deltaTime );
                        // if the current action is finished, next time, move on to next action
                        if (finished == true)
                            actionIndex++;
                        // if all actions are finished, the sequence is finished
                        boolean sequenceFinished = (actionIndex == actionArray.length);

                        return sequenceFinished;
                    }
                };
            }
            // override the reset function
            public void reset()
            {
                // reset all Actions contained in actionArray.
                for (int i = 0; i < actionArray.length; i++)
                {
                    Action act = actionArray[i];
                    act.reset();

                    // start sequence at index 0 again
                    actionIndex = 0;
                }
            }
        };
    }

    /**
     * Repeats an action by a given number of times
     *  by resetting it every time it is finished.
     *
     * @param act the action to repeat
     * @param numberOfTimes how many times the action should be repeated
     * @return Action
     */
    public static Action repeat(Action act, int numberOfTimes)
    {
        Function func = new Function()
            {
                public int finishedCount = 0;

                public boolean run(Sprite target, double deltaTime, double totalTime)
                {
                    boolean finished = act.apply(target, deltaTime);
                    if (finished == true)
                    {
                        act.reset();
                        finishedCount++;
                    }

                    return (finishedCount == numberOfTimes);
                }
            };

        return new Action(func);
    }

    
    
}
