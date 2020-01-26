package will.example.myworrytime;

public class DigitalClockView {
    //The time elements
    private long mStartTime;
    private long mTimeUpdate;
    private long mStoredTime;

    public DigitalClockView() {
        mStartTime = 0L;
        mTimeUpdate = 0L;
        mStoredTime = 0L;
    }

    public void resetWatchTime() {
        mStartTime = 0L;
        mTimeUpdate = 0L;
        mStoredTime = 0L;
    }

    public void setStartTime(long startTime) {
        mStartTime = startTime;
    }

    public long getStartTime(){
        return mStartTime;
    }

    public void setTimeUpdate(long timeUpdate){
        mTimeUpdate = timeUpdate;
    }

    public long getTimeUpdate(){
        return mTimeUpdate;
    }

    public void addStoredTime(long timeInMilliseconds){
        mStoredTime += timeInMilliseconds;
    }

    public long getStoredTime(){
        return mStoredTime;
    }
}
