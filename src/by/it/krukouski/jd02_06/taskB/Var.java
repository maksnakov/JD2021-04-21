package by.it.krukouski.jd02_06.taskB;

abstract class Var implements Operation {
    @Override
    public Var add(Var other) throws CalcException {
        throw new CalcException(ConsoleRunner.manager.get(Errors.operationImpossible));
    }

    @Override
    public Var sub(Var other) throws CalcException {
        throw new CalcException(ConsoleRunner.manager.get(Errors.operationImpossible));
    }

    @Override
    public Var mul(Var other) throws CalcException {
        throw new CalcException(ConsoleRunner.manager.get(Errors.operationImpossible));
    }

    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException(ConsoleRunner.manager.get(Errors.operationImpossible));
    }

    public String toString() {
        return ConsoleRunner.manager.get(Errors.abstractVariable);
    }
}
