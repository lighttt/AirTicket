package np.com.manishtuladhar.airticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import np.com.manishtuladhar.airticket.databinding.ActivityMainBinding;
import np.com.manishtuladhar.airticket.utils.FakeAirTicketData;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    private static final String TAG = "MainActivity";
    CountDownTimer countDownTimer = null;
    AirTicketPass fakeAirTicketData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize data binding with view
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        if(savedInstanceState==null)
        {
            fakeAirTicketData = FakeAirTicketData.generateFakeAirTicketData();
        }
        else{
            fakeAirTicketData = savedInstanceState.getParcelable("airticket");
        }
        //time
        if (fakeAirTicketData != null) {
            timeFormats(fakeAirTicketData);
            //image
            mBinding.barCode.setImageResource(fakeAirTicketData.barCodeImageResource);
            //display
            mBinding.setAirticket(fakeAirTicketData);
        }
        }


    private void timeFormats(AirTicketPass ticketPass)
    {
        //time format
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String boardingTime = formatter.format(ticketPass.boardingTime);
        String departureTime = formatter.format(ticketPass.departureTime);
        String arrivalTime = formatter.format(ticketPass.arrivalTime);

        mBinding.tvArrivalTime.setText(arrivalTime);
        mBinding.tvBoardingTime.setText(boardingTime);
        mBinding.tvDeparture.setText(departureTime);

     //count down time
        countDownTimer = new CountDownTimer(ticketPass.getMillisUntilBoarding(),60000){
            @Override
            public void onTick(long millisUntilFinished) {
                long totalMinutesUntilBoarding = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                Log.e(TAG, "onTick: "+totalMinutesUntilBoarding);
                long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
                Log.e(TAG, "onTick: "+hoursUntilBoarding);
                long minuteLessUntilBoarding =
                        totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);
                Log.e(TAG, "onTick: "+minuteLessUntilBoarding);
                String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,hoursUntilBoarding,minuteLessUntilBoarding);
                Log.e(TAG, "onTick: "+hoursAndMinutesUntilBoarding);
                mBinding.tvBoardingInTime.setText(hoursAndMinutesUntilBoarding);
            }

            @Override
            public void onFinish() {
                mBinding.tvBoardingInTime.setText("Completed");
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
        {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
       outState.putParcelable("airticket",fakeAirTicketData);
    }
}