package Task1;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.Timer;

public class AnalogClock extends JPanel {
    private int hour;
    private int minute;
    private int minInc_counter=0;
    private int hourInc_counter=0;
    public AnalogClock() {
        Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setStroke(new BasicStroke(3));


        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) /2 - 5;


        //Малює задній фон годинника
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillOval(width / 2 - radius, height / 2 - radius, radius * 2, radius * 2);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawOval(width / 2 - radius, height / 2 - radius, radius * 2, radius * 2);

        // Малює цифри на годиннику
        for (int i = 1; i <= 12; i++) {
            double angle = Math.PI / 6 * (i - 3);
            int x = (int) (width / 2 + (radius - 15) * Math.cos(angle));
            int y = (int) (height / 2 + (radius - 15) * Math.sin(angle));
            graphics2D.drawString(Integer.toString(i), x, y);
        }

        //Часова стрілка
        double hourAngle = Math.PI / 6 * (hour - 3) + Math.PI / 360 * minute;
        Line2D.Double hourHand = new Line2D.Double(width / 2, height / 2, width / 2 + radius / 2 * Math.cos(hourAngle), height / 2 + radius / 2 * Math.sin(hourAngle));
        graphics2D.draw(hourHand);

        //Хвилинна стрілка
        double minuteAngle = Math.PI / 30 * (minute - 15) + Math.PI / 1800 ;
        Line2D.Double minuteHand = new Line2D.Double(width / 2, height / 2, width / 2 + radius / 1.5 * Math.cos(minuteAngle), height / 2 + radius / 1.5 * Math.sin(minuteAngle));
        graphics2D.draw(minuteHand);

        //Кнопки (+ -) для годин хвилин
        JButton hourInc_button = new JButton("+ H");
        hourInc_button.addActionListener(e -> {
            hour = (hour + 1) % 12;
            hourInc_counter++;
            repaint();
        });

        JButton houDec_button = new JButton("- H");
        houDec_button.addActionListener(e -> {
            hourInc_counter--;
            hour = (hour - 1) % 12;
            if (hour < 1) {
                hour += 12;
            }
            repaint();
        });

        JButton minuteInc_button = new JButton("+M");
        minuteInc_button.addActionListener(e -> {
            minInc_counter++;
            minute = (minute + 1) % 60;
            repaint();
        });

        JButton minuteDec_button = new JButton("- M");
        minuteDec_button.addActionListener(e -> {
            minInc_counter--;
            minute = (minute - 1) % 60;
            if (minute < 0) {
                minute += 60;
                hour = (hour - 1) % 12;
                if (hour < 1) {
                    hour += 12;
                }
            }
            repaint();
        });


        //Таймер для оновлення часу на годиннику кожну секунду
        Timer timer = new Timer(1000, e -> {
            //Отримати час
            Calendar cal = Calendar.getInstance();
            int currentHour = cal.get(Calendar.HOUR);
            int currentMinute = cal.get(Calendar.MINUTE);
            //Перевірка чи час на годиннику відрізняється від поточного
            if (currentHour != hour || currentMinute != minute) {
                hour = currentHour+hourInc_counter;
                minute = currentMinute+minInc_counter;
                repaint();
            }
        });
        timer.start();
        //Межі кнопок
        hourInc_button.setBounds(5, height - 40, 55, 35);
        houDec_button.setBounds(60, height - 40, 55, 35);
        minuteInc_button.setBounds(width - 115, height - 40, 55, 35);
        minuteDec_button.setBounds(width - 60, height - 40, 55, 35);
        //Додати кнопки на панель
        add(hourInc_button);
        add(houDec_button);
        add(minuteInc_button);
        add(minuteDec_button);
    }

    public static void main(String[] args) {
        JFrame panel = new JFrame("Analog Clock");
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(500, 500);
        AnalogClock analogClock = new AnalogClock();
        panel.add(analogClock);
        panel.setVisible(true);
    }
}