package np.com.manishtuladhar.airticket.utils;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import np.com.manishtuladhar.airticket.AirTicketPass;
import np.com.manishtuladhar.airticket.R;

public class FakeAirTicketData {

    /**
     * Insert fake data to the view
     */
    public static AirTicketPass generateFakeAirTicketData()
    {
        AirTicketPass airTicketPass = new AirTicketPass();

        airTicketPass.passengerName = "MR. SAMIP GYAWALI";
        airTicketPass.flightCode = "SG 777";
        airTicketPass.originCode = "TIA";
        airTicketPass.destCode = "PKR";

        long now = System.currentTimeMillis();

        //ticket time
        long randomMinUntilBoarding = (long) (Math.random() * 30);
        //standard waiting time until boarding
        long totalBoardingMinutes = 40;
        //flight length
        long randomFlightLengthHours = (long) (Math.random() * 5 + 1);

        long boardInMillis = now + minutesToMillis(randomMinUntilBoarding);
        long departure = boardInMillis + minutesToMillis(totalBoardingMinutes);
        long arrival = departure + hoursToMillis(randomFlightLengthHours);

        airTicketPass.boardingTime = new Timestamp(boardInMillis);
        airTicketPass.departureTime = new Timestamp(departure);
        airTicketPass.arrivalTime = new Timestamp(arrival);

        airTicketPass.departureTerminal = "3C";
        airTicketPass.departureGate = "1A";
        airTicketPass.seatNumber = "21D";
        airTicketPass.barCodeImageResource = R.drawable.barcode;

        return airTicketPass;
    }

    private static long minutesToMillis(long minutes)
    {
        return TimeUnit.MINUTES.toMillis(minutes);
    }

    private static long hoursToMillis(long hours)
    {
        return TimeUnit.HOURS.toMillis(hours);
    }
}
