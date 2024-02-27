public class Algoritmos {

    private int np = 0;

    private String[] nom = new String[99]; //Proceso
    private int[] ini = new int[99]; //Inicio
    private int[] cpu = new int[99]; //Tiempo cpu
    private int[] fin = new int[99]; //Fin
    private int[] T = new int[99]; //Tiempo
    private int[] E = new int[99]; //Espera
    private float[] I = new float[99]; //Penalizacion
    private int [] p = new int[99]; //Prioridad

    private String[] nomOrd = new String[99];
    private int[] cpuOrd = new int[99];
    private int[] iniOrd = new int[99];
    private int[] finOrd = new int[99];
    private int[] Tord = new int[99];
    private int[] Eord = new int[99];
    private float[] Iord = new float[99];
    private int [] Pord = new int[99];

    public Algoritmos() {
    }

    public void adicionarProceso(String nombre,int ti, int tcpu, int prio) {
        nom[np] = nombre;
        ini[np] = ti;
        cpu[np] = tcpu;
        p[np] = prio;
        ++np;
    }


    public String getName(int i) {
        return nom[i];
    }
    public void fifo() {
        fin[0] = ini[0] + cpu[0];
        int p = fin[0];
        int i = 1;
        int ocio = 0;

        while (i < np) {
            if (ini[i] <= p) {
                fin[i] = cpu[i] + fin[i - 1] + ocio;
                ocio = 0;
                p = fin[i];
                ++i;
            } else {
                ++ocio;
                ++p;
            }
        }
        calcularTEI();
    }
    public void prio(){
        burbuja2();
        finOrd[0] = iniOrd[0] + cpuOrd[0];
        int p = finOrd[0];
        int i = 1;
        int ocio = 0;

        while (i < np) {
            if (iniOrd[i] <= p) {
                finOrd[i] = cpuOrd[i] + finOrd[i - 1] + ocio;
                ocio = 0;
                p = finOrd[i];
                ++i;
            } else {
                ++ocio;
                ++p;
            }
        }
        calcularTEIOrd();
    }
    public void rr() {
        int quantum = 3;
        boolean su = false;
        int s = 1;
        int u = 0;
        boolean c = false;
        boolean x = false;
        boolean y = false;
        int b = 0;
        int a = 0;
        int var13 = ini[0];
        int[] cpu2 = new int[np + 1];

        for (int k = 0; k < np; ++k) {
            cpu2[k] = cpu[k];
        }

        while (true) {
            while (s <= np) {
                for (int j = 0; j < np; ++j) {
                    if (cpu2[j] != 0) {
                        ++var13;
                        --cpu2[j];
                        if (cpu2[j] == 0) {
                            fin[j] = var13;
                            ++s;
                            if (ini[j + 1] > var13) {
                                a = j;
                                su = true;
                                u = ini[j + 1];
                            }
                        }

                        if (ini[j + 1] > var13) {
                            int var14 = 0;

                            for (int i = 0; i < a; ++i) {
                                if (cpu2[i] != 0) {
                                    ++var14;
                                    b = i;
                                }
                            }

                            if (var14 == 1) {
                                var13 += cpu2[b];
                                fin[b] = var13;
                                cpu2[b] = 0;
                                x = false;
                                ++s;
                            }
                            break;
                        }
                    } else if (su) {
                        var13 += u - var13;
                        su = false;
                        a = 0;
                    }
                }
            }

            calcularTEI();
            return;
        }
    }
    public void sjf(){
        burbuja();
        finOrd[0] = iniOrd[0] + cpuOrd[0];
        int p = finOrd[0];
        int i = 1;
        int ocio = 0;

        while (i < np) {
            if (iniOrd[i] <= p) {
                finOrd[i] = cpuOrd[i] + finOrd[i - 1] + ocio;
                ocio = 0;
                p = finOrd[i];
                ++i;
            } else {
                ++ocio;
                ++p;
            }
        }
        calcularTEIOrd();
    }
    public void burbuja() {
        nomOrd = nom;
        cpuOrd = cpu;
        iniOrd = ini;
        Pord = p;

        String tmp1;
        int tmp2;

        for (int i = 0; i < np; i++) {
            for (int j = 1; j < (np - i); j++) {
                if (Pord[j - 1] > Pord[j]) {

                    tmp1 = nomOrd[j - 1];
                    nomOrd[j - 1] = nomOrd[j];
                    nomOrd[j] = tmp1;

                    tmp2 = cpuOrd[j - 1];
                    cpuOrd[j - 1] = cpuOrd[j];
                    cpuOrd[j] = tmp2;

                    tmp2 = iniOrd[j - 1];
                    iniOrd[j - 1] = iniOrd[j];
                    iniOrd[j] = tmp2;

                    tmp2 = Pord[j - 1];
                    Pord[j - 1] = Pord[j];
                    Pord[j] = tmp2;
                }

            }
        }
    }
    public void burbuja2() {
        nomOrd = nom;
        cpuOrd = cpu;
        iniOrd = ini;
        Pord = p;

        String tmp1;
        int tmp2;

        for (int i = 0; i < np; i++) {
            for (int j = 1; j < (np - i); j++) {
                if (Pord[j - 1] > Pord[j]) {

                    tmp1 = nomOrd[j - 1];
                    nomOrd[j - 1] = nomOrd[j];
                    nomOrd[j] = tmp1;

                    tmp2 = cpuOrd[j - 1];
                    cpuOrd[j - 1] = cpuOrd[j];
                    cpuOrd[j] = tmp2;

                    tmp2 = iniOrd[j - 1];
                    iniOrd[j - 1] = iniOrd[j];
                    iniOrd[j] = tmp2;

                    tmp2 = Pord[j - 1];
                    Pord[j - 1] = Pord[j];
                    Pord[j] = tmp2;
                }

            }
        }
    }
    public void calcularTEI() {
        for (int i = 0; i < np; ++i) {
            T[i] = fin[i] - getIni()[i];
            E[i] = T[i] - cpu[i];
            I[i] = (float) cpu[i] / (float) T[i];
        }
    }
    public void calcularTEIOrd() {
        for (int i = 0; i < np; ++i) {
            Tord[i] = finOrd[i] - getIni()[i];
            Eord[i] = Tord[i] - cpuOrd[i];
            Iord[i] = (float) cpuOrd[i] / (float) Tord[i];
        }
    }
    public int getNP() {
        return np;
    }
    public String[][] getDatosI() {
        String[][] X = new String[np][8];

        for (int i = 0; i < np; ++i) {
            X[i][0] = nom[i];
            X[i][1] = String.valueOf(p[i]);
            X[i][2] = String.valueOf(getIni()[i]);
            X[i][3] = String.valueOf(cpu[i]);
            X[i][4] = null;
            X[i][5] = null;
            X[i][6] = null;
            X[i][7] = null;
        }

        return X;
    }
    public String[][] getDatos() {
        String[][] X = new String[np][8];

        for (int i = 0; i < np; ++i) {
            X[i][0] = nom[i];
            X[i][1] = String.valueOf(p[i]);
            X[i][2] = String.valueOf(getIni()[i]);
            X[i][3] = String.valueOf(cpu[i]);
            X[i][4] = String.valueOf(fin[i]);
            X[i][5] = String.valueOf(T[i]);
            X[i][6] = String.valueOf(E[i]);
            X[i][7] = String.valueOf(I[i]);

        }

        return X;
    }
    public String[][] getDatosOrd(){
        String[][] X = new String[np][8];

        for (int i = 0; i < np; ++i) {
            X[i][0] = nomOrd[i];
            X[i][1] = String.valueOf(Pord[i]);
            X[i][2] = String.valueOf(iniOrd[i]);
            X[i][3] = String.valueOf(cpuOrd[i]);
            X[i][4] = String.valueOf(finOrd[i]);
            X[i][5] = String.valueOf(Tord[i]);
            X[i][6] = String.valueOf(Eord[i]);
            X[i][7] = String.valueOf(Iord[i]);

        }

        return X;
    }
    public int[][] getDatos2() {
        int[][] X = new int[np][6];

        for (int i = 0; i < np; ++i) {
            X[i][0] = getIni()[i];
            X[i][1] = cpu[i];
            X[i][2] = fin[i];
            X[i][3] = T[i];
            X[i][4] = E[i];
            X[i][5] = p[i];
        }

        return X;
    }
    public int[][] getDatos2Ord(){
        int[][] X = new int[np][6];

        for (int i = 0; i < np; ++i) {
            X[i][0] = getIniOrd()[i];
            X[i][1] = Pord[i];
            X[i][2] = cpuOrd[i];
            X[i][3] = finOrd[i];
            X[i][4] = Tord[i];
            X[i][5] = Eord[i];
        }

        return X;
    }
    public int maximoY() {
        int max = 0;

        for (int i = 0; i < np; ++i) {
            if (fin[i] > max) {
                max = fin[i];
            }
        }

        return max;
    }
    public int[] getIni() {
        return ini;
    }
    public int[] getIniOrd(){
        return iniOrd;
    }
}