import java.time.LocalDate;
import java.util.Arrays;

public class Calendario {

    static final int squadra = 2;
    LocalDate[] data;
    int count;
    String[][] incontri;

    public Calendario() {
        this.data = new LocalDate[count];
        this.incontri = new String[count][squadra];
    }

    public void getData(int aa, int mm, int gg) {
        data[count] = LocalDate.of(aa, mm, gg);
    }

    public void incontri(String s1, String s2, int aa, int mm, int gg) {

        getData(aa, mm, gg);
        incontri[count][0] = s1;
        incontri[count][1] = s2;
    }

    public void add(String s1, String s2, int aa, int mm, int gg) {
        data = Arrays.copyOf(data, count+1);

        incontri = Arrays.copyOf(incontri, count+1);
        incontri[count] = new String[squadra];

        incontri(s1, s2, aa, mm, gg);

        count++;
    }

}
