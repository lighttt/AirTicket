package np.com.manishtuladhar.airticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import np.com.manishtuladhar.airticket.databinding.ActivityMainBinding;
import np.com.manishtuladhar.airticket.utils.FakeAirTicketData;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize data binding with view
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //class
        AirTicketPass fakeAirTicketData = FakeAirTicketData.generateFakeAirTicketData();
        //time
        timeFormats(fakeAirTicketData);
        //image
        mBinding.barCode.setImageResource(fakeAirTicketData.barCodeImageResource);
        //display
        mBinding.setAirticket(fakeAirTicketData);
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
        long totalMinutesUntilBoarding = ticketPass.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minuteLessUntilBoarding =
        totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,hoursUntilBoarding,minuteLessUntilBoarding);

        mBinding.tvBoardingInTime.setText(hoursAndMinutesUntilBoarding);

    }
}