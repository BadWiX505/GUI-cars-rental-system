package Tools;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLabel extends JLabel {

    public TimeLabel() {
        setFont(new Font("Arial", Font.BOLD, 24));
        setHorizontalAlignment(SwingConstants.CENTER);

        Timer timer = new Timer(1000, e -> updateCurrentTime());
        timer.start();

        updateCurrentTime();
    }

    private void updateCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        setText(currentTime);
    }
}
