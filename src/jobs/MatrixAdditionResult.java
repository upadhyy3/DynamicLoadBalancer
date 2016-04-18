package jobs;

public class MatrixAdditionResult implements JobResult{

    private Float result;

    public MatrixAdditionResult() {
        result = 0.0f;
    }

    public MatrixAdditionResult(Float r) {
        result = r;
    }

    public String getResult() {
        return result.toString();
    }

    public String toString() {
        return getResult();
    }

    public JobResult aggregate(JobResult result) {
        Float r = Float.valueOf(result.getResult());
        return new MatrixAdditionResult(r + this.result);
    }
}
