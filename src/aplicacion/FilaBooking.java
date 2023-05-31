

package aplicacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Booking;
import model.Court;
import model.Member;

/**
 *
 * @author joan
 */
public class FilaBooking {

    private final Booking booking;
    private final LocalDate date;
    private final LocalTime fromTime;
    private final LocalTime toTime;
    private final Court pista;
    private final Member reservado;
    
    public FilaBooking(Booking booking) {
        this.booking = booking;
        this.date = booking.getMadeForDay();
        this.fromTime = booking.getFromTime();
        this.toTime = booking.getFromTime().plusHours(1);
        this.pista = booking.getCourt();
        this.reservado = booking.getMember();
    }
    
    public FilaBooking(LocalDate date, LocalTime fromTime, Court pista, Member reservado) {
        this.booking = null;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = fromTime.plusHours(1);
        this.pista = pista;
        this.reservado = reservado;
    }

    public Booking getBooking() {
        return booking;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public Court getPista() {
        return pista;
    }

    public Member getReservado() {
        return reservado;
    }
    
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(date, fromTime);
    }

    public void cancelBooking() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
