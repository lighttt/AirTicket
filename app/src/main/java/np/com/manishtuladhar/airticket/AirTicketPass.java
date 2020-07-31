package np.com.manishtuladhar.airticket;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class AirTicketPass implements Parcelable {

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

    public AirTicketPass()
    {

    }

    public AirTicketPass(Parcel in) {
        passengerName = in.readString();
        flightCode = in.readString();
        originCode = in.readString();
        destCode = in.readString();
        departureTerminal = in.readString();
        departureGate = in.readString();
        seatNumber = in.readString();
        barCodeImageResource = in.readInt();
    }

    public static final Creator<AirTicketPass> CREATOR = new Creator<AirTicketPass>() {
        @Override
        public AirTicketPass createFromParcel(Parcel in) {
            return new AirTicketPass(in);
        }

        @Override
        public AirTicketPass[] newArray(int size) {
            return new AirTicketPass[size];
        }
    };

    public long getMillisUntilBoarding()
    {
        return boardingTime.getTime() - System.currentTimeMillis();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(passengerName);
        parcel.writeString(flightCode);
        parcel.writeString(originCode);
        parcel.writeString(destCode);
        parcel.writeString(departureTerminal);
        parcel.writeString(departureGate);
        parcel.writeString(seatNumber);
        parcel.writeInt(barCodeImageResource);
    }
}
