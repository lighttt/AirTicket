package np.com.manishtuladhar.airticket;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class AirTicketPass {

    public String passengerName;
    public String flightCode;
    public String originCode;
    public String destCode;


    public String departureTerminal;
    public String departureGate;
    public String seatNumber;

    public Timestamp boardingTime;
    public Timestamp departureTime;
    public Timestamp arrivalTime;

    public int barCodeImageResource;

    public long getMinutesUntilBoarding()
    {
        long millisUntilBoarding = boardingTime.getTime() - System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toMinutes(millisUntilBoarding);
    }

}
