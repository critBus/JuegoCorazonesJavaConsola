/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complet.VisualConsola;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Rene
 */
public class JuegoEnUnaClase {

    static class TipoDeNumero {

        public static final TipoDeNumero N_A = new TipoDeNumero(14, "A");
        public static final TipoDeNumero N_2 = new TipoDeNumero(2, "2");
        public static final TipoDeNumero N_3 = new TipoDeNumero(3, "3");
        public static final TipoDeNumero N_4 = new TipoDeNumero(4, "4");
        public static final TipoDeNumero N_5 = new TipoDeNumero(5, "5");
        public static final TipoDeNumero N_6 = new TipoDeNumero(6, "6");
        public static final TipoDeNumero N_7 = new TipoDeNumero(7, "7");
        public static final TipoDeNumero N_8 = new TipoDeNumero(8, "8");
        public static final TipoDeNumero N_9 = new TipoDeNumero(9, "9");
        public static final TipoDeNumero N_10 = new TipoDeNumero(10, "10");
        public static final TipoDeNumero N_J = new TipoDeNumero(11, "J");
        public static final TipoDeNumero N_Q = new TipoDeNumero(12, "Q");
        public static final TipoDeNumero N_K = new TipoDeNumero(13, "K");

        public static final TipoDeNumero[] VALUES = {N_A, N_2, N_3, N_4, N_5, N_6, N_7, N_8, N_9, N_10, N_J, N_Q, N_K};
        private final int valor;
        private final String numero;

        public TipoDeNumero(int valor, String numero) {
            this.valor = valor;
            this.numero = numero;
        }

        public int getValor() {
            return valor;
        }

        public String getNombreDelNumero() {
            return numero;
        }

    }

    static class TipoDePalo {

        public static TipoDePalo CORAZONES = new TipoDePalo("CORAZONES"), PICA = new TipoDePalo("PICA"), TREVOL = new TipoDePalo("TREVOL"), DIAMANTE = new TipoDePalo("DIAMANTE");

        public final static TipoDePalo VALUES[] = {CORAZONES, PICA, TREVOL, DIAMANTE};

        private final String palo;

        public TipoDePalo(String palo) {
            this.palo = palo;
        }

        public String getNombreDelPalo() {
            return palo;
        }

    }

    static class Carta {

        private TipoDePalo palo;
        private TipoDeNumero numero;

        public Carta(TipoDePalo palo, TipoDeNumero numero) {
            this.palo = palo;
            this.numero = numero;
        }

        public TipoDePalo getPalo() {
            return palo;
        }

        public void setPalo(TipoDePalo palo) {
            this.palo = palo;
        }

        public TipoDeNumero getNumero() {
            return numero;
        }

        public void setNumero(TipoDeNumero numero) {
            this.numero = numero;
        }

        public boolean esIguala(Carta c) {
//        System.out.println("palo="+palo.getNombreDelPalo());
//        System.out.println("c.palo="+c.palo.getNombreDelPalo());
//        System.out.println("numero="+numero.getNombreDelNumero());
//        System.out.println("c.numero="+c.numero.getNombreDelNumero());
//        System.out.println("com="+(palo==c.palo&&numero==c.numero));
            return palo == c.palo && numero == c.numero;
        }

        public boolean esMayorQue(Carta c) {
            return this.numero.getValor() > c.getNumero().getValor();
        }

        @Override
        public String toString() {
            return palo.getNombreDelPalo() + "-" + numero.getNombreDelNumero(); //To change body of generated methods, choose Tools | Templates.
        }

    }

    static class ManoDeCartas {

        private Carta[] cartas;

        public ManoDeCartas(Carta[] cartas) {
            this.cartas = cartas;
        }

        public boolean tengo2DeTrebol() {
            for (int i = 0; i < cartas.length; i++) {
                Carta c = cartas[i];
                if (c.getPalo() == TipoDePalo.TREVOL && c.getNumero() == TipoDeNumero.N_2) {
                    return true;
                }
            }
            return false;
        }

        public void quitarCartas(Carta[] cartasAquitar) {
            Carta[] temporales = new Carta[cartas.length - cartasAquitar.length];
            int contador = 0;
            For1:
            for (int i = 0; i < cartas.length; i++) {
                Carta actual = cartas[i];
                for (int j = 0; j < cartasAquitar.length; j++) {
//                System.out.println("cartas[i]=" + cartas[i] + " cartasAquitar[j]=" + cartasAquitar[j]);
                    if (actual.esIguala(cartasAquitar[j])) {
                        continue For1;
                    }
                }
                temporales[contador] = cartas[i];
                contador++;
            }

//        For1:
//        for (int i = 0; i < cartas.length; i++) {
//
//            for (int j = 0; j < cartasAquitar.length; j++) {
//                System.out.println("cartas[i]=" + cartas[i] + " cartasAquitar[j]=" + cartasAquitar[j]);
//                if (!cartas[i].esIguala(cartasAquitar[j])) {
//                    temporales[contador] = cartas[i];
//                    continue For1;
//                }
//            }
//
//            contador++;
//
//        }
            this.cartas = temporales;
//        for (int i = 0; i < cartas.length; i++) {
//            System.out.println("carta " + cartas[i]);
//        }
//        System.out.println("-----------------------------");

        }

        public void agregarCartas(Carta[] cartasAAgregar) {
            Carta[] temporales = new Carta[cartas.length + cartasAAgregar.length];
            for (int i = 0; i < cartas.length; i++) {
                temporales[i] = cartas[i];
            }
            for (int i = 0; i < cartasAAgregar.length; i++) {
                temporales[i + cartas.length] = cartasAAgregar[i];
            }
            this.cartas = temporales;
        }

        public Carta[] getCartas() {
            return cartas;
        }

        public boolean tieneCartasAll(Carta[] cartasAComprobar) {
            For1:
            for (int j = 0; j < cartasAComprobar.length; j++) {

                for (int i = 0; i < cartas.length; i++) {
                    Carta cartaActualEnMano = cartas[i];
                    Carta cartaActualAComprobar = cartasAComprobar[j];
//                System.out.println("cartaActualEnMano=" + cartaActualEnMano);
//                System.out.println("cartaActualAComprobar=" + cartaActualAComprobar);
                    if (cartaActualEnMano.esIguala(cartaActualAComprobar)) {
                        continue For1;
                    }

                }
                return false;
            }

            //System.out.println("dio true");
            return true;
        }

    }

    static class Jugador {

        private String nombre;
        private ManoDeCartas manoDeCartas;
        private int puntos;

        private Jugador jugadorALaDerecha, jugadorALaIzquierda, jugadorAlFrente;

        public Jugador(String nombre) {
            this.nombre = nombre;
            this.puntos = 0;
        }

        public void agregarPuntos(int cantidad) {
            this.puntos += cantidad;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public ManoDeCartas getManoDeCartas() {
            return manoDeCartas;
        }

        public void setManoDeCartas(ManoDeCartas manoDeCartas) {
            this.manoDeCartas = manoDeCartas;
        }

        public int getPuntos() {
            return puntos;
        }

        public void setPuntos(int puntos) {
            this.puntos = puntos;
        }

        public Jugador getJugadorALaDerecha() {
            return jugadorALaDerecha;
        }

        public void setJugadorALaDerecha(Jugador jugadorALaDerecha) {
            this.jugadorALaDerecha = jugadorALaDerecha;
        }

        public Jugador getJugadorALaIzquierda() {
            return jugadorALaIzquierda;
        }

        public void setJugadorALaIzquierda(Jugador jugadorALaIzquierda) {
            this.jugadorALaIzquierda = jugadorALaIzquierda;
        }

        public Jugador getJugadorAlFrente() {
            return jugadorAlFrente;
        }

        public void setJugadorAlFrente(Jugador jugadorAlFrente) {
            this.jugadorAlFrente = jugadorAlFrente;
        }

    }

    static class Baraja {

        private Carta[] cartas;

        public Baraja() {
            resetar();
        }

        private void resetar() {
            this.cartas = new Carta[52];
            int count = 0;
            for (int i = 0; i < TipoDePalo.VALUES.length; i++) {
                for (int j = 0; j < TipoDeNumero.VALUES.length; j++) {
                    cartas[count] = new Carta(TipoDePalo.VALUES[i], TipoDeNumero.VALUES[j]);
                    count++;
                }
            }
        }

        public void barajear() {
            Random rdm = new Random();

            for (int i = 0; i < cartas.length; i++) {
                int posAleatoria = rdm.nextInt(cartas.length);
                Carta temp = cartas[i];
                cartas[i] = cartas[posAleatoria];
                cartas[posAleatoria] = temp;
            }
        }

        public void repartir(Jugador J[]) {
            int count = 0;
            int indiceJugadorActual = 0;
            Carta cartasEnMano[] = new Carta[13];
            for (int i = 0; i < cartas.length; i++) {
                cartasEnMano[count] = cartas[i];
                if (count == 12) {
                    J[indiceJugadorActual].setManoDeCartas(new ManoDeCartas(cartasEnMano));
                    count = 0;
                    indiceJugadorActual++;
                    cartasEnMano = new Carta[13];
                    continue;
                }
                count++;
            }

        }
    }

    static class CartaEnMesa {

        private Jugador jugador;
        private Carta carta;

        public CartaEnMesa(Jugador jugador, Carta carta) {
            this.jugador = jugador;
            this.carta = carta;
        }

        public Carta getCarta() {
            return carta;
        }

        public void setCarta(Carta carta) {
            this.carta = carta;
        }

        public Jugador getJugador() {
            return jugador;
        }

        public void setJugador(Jugador jugador) {
            this.jugador = jugador;
        }

    }

    static class DatosRealizarTurnoJugadorActual {

        public boolean seRecojieronLasCartasPqFueLaCuarta;
        public Jugador jugadorQueSellevoLosPuntos;
        public int cantidadDePuntosQueSeLlevo;

    }

    static class Mesa {

        private CartaEnMesa[] cartasEnMesa;

        public Mesa() {
            this.cartasEnMesa = new CartaEnMesa[0];
        }

        public void agregarCarta(Jugador j, Carta c) {
            CartaEnMesa[] temporal = new CartaEnMesa[cartasEnMesa.length + 1];
            for (int i = 0; i < cartasEnMesa.length; i++) {
                temporal[i] = cartasEnMesa[i];
            }
            temporal[temporal.length - 1] = new CartaEnMesa(j, c);
            cartasEnMesa = temporal;
        }

        public boolean hay4Cartas() {
            return cartasEnMesa.length == 4;
        }

        public boolean sonDelMismoPalo() {
            TipoDePalo paloActual = null;
            for (int i = 0; i < cartasEnMesa.length; i++) {
                Carta cartaActual = cartasEnMesa[i].getCarta();
                if (i == 0) {
                    paloActual = cartaActual.getPalo();
                    continue;
                }
                if (cartaActual.getPalo() != paloActual) {
                    return false;
                }
            }
            return true;
        }

        public Jugador getJugadorDeMayorCarta() {
            if (hay4Cartas()) {
                CartaEnMesa mayorActual = cartasEnMesa[0];

                Carta primeraCarta = mayorActual.getCarta();
                TipoDePalo paloDePrimeraCarta = primeraCarta.getPalo();

                for (int i = 1; i < cartasEnMesa.length; i++) {
                    CartaEnMesa cartaActual = cartasEnMesa[i];
                    if (cartaActual.getCarta().getPalo() == paloDePrimeraCarta
                            && cartaActual.getCarta().esMayorQue(mayorActual.getCarta())) {
                        mayorActual = cartasEnMesa[i];
                    }
                }
                return mayorActual.getJugador();
            }
            return null;
        }

        public int getCantidadDePuntos() {
            int total = 0;
            for (int i = 1; i < cartasEnMesa.length; i++) {
                Carta cartaActual = cartasEnMesa[i].getCarta();
                if (cartaActual.getPalo() == TipoDePalo.CORAZONES) {
                    total++;
                    continue;
                }
                if (cartaActual.getPalo() == TipoDePalo.PICA && cartaActual.getNumero() == TipoDeNumero.N_Q) {
                    total += 13;
                }
            }
            return total;
        }

        public CartaEnMesa[] getCartasEnMesa() {
            return cartasEnMesa;
        }

    }
    
    
    static class TablaPosiciones {

    private Jugador[] jugadores;

    public TablaPosiciones(Jugador[] jugadores) {
        Arrays.sort(jugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador o1, Jugador o2) {
                if (o1.getPuntos() == o2.getPuntos()) {
                    return 0;
                }
                if (o1.getPuntos() > o2.getPuntos()) {
                    return 1;
                } else {
                    return -1;
                }
                //return o1.getPuntos() > o2.getPuntos() ? 1 : -1;  ()?(true):(false)
            }
        });
        this.jugadores=jugadores;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

}
    
    
    static class JuegoDecorazones {

    private boolean juegoTerminado;
    private Jugador jugador1, jugador2, jugador3, jugador4;
    private int turnoActual;//1 Isquierda ,2 Derecha 3,nada
    private int turnoDeJugador;//0-3
    private int cartaActual;//0-12

    private Mesa mesa;

    public JuegoDecorazones() {
        juegoTerminado = true;
    }

    public void comenzarJuego(Jugador jugadores[]) {
        juegoTerminado = false;
        jugador1 = jugadores[0];
        jugador2 = jugadores[1];
        jugador3 = jugadores[2];
        jugador4 = jugadores[3];

        jugador1.setJugadorALaDerecha(jugador2);
        jugador1.setJugadorALaIzquierda(jugador4);
        jugador1.setJugadorAlFrente(jugador3);

        jugador2.setJugadorALaDerecha(jugador3);
        jugador2.setJugadorALaIzquierda(jugador1);
        jugador2.setJugadorAlFrente(jugador4);

        jugador3.setJugadorALaDerecha(jugador4);
        jugador3.setJugadorALaIzquierda(jugador2);
        jugador3.setJugadorAlFrente(jugador1);

        jugador4.setJugadorALaDerecha(jugador1);
        jugador4.setJugadorALaIzquierda(jugador3);
        jugador4.setJugadorAlFrente(jugador2);
        turnoDeJugador = 0;
        turnoActual = 1;
        cartaActual = 13;
        //cartaActual = 0;
        mesa = new Mesa();
        barajearYRepartir();
    }

    public void barajearYRepartir() {
        Jugador jugadores[] = {jugador1, jugador2, jugador3, jugador4};
        Baraja baraja = new Baraja();
        baraja.barajear();
        baraja.repartir(jugadores);
        decidirTurnoDelJugador();
    }

    public void decidirTurnoDelJugador() {
        Jugador jugadores[] = {jugador1, jugador2, jugador3, jugador4};
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i].getManoDeCartas().tengo2DeTrebol()) {
                turnoDeJugador = i;
                break;
            }
        }
    }

    public void pasarTurno(Carta[] cartasSeleccionada) {
        //va a tener 12 cartas , 3 por jugador
//        for (int i = 0; i < cartasSeleccionada.length; i++) {
//            System.out.println("c="+cartasSeleccionada[i]);
//        }
        jugador1.getManoDeCartas().quitarCartas(new Carta[]{cartasSeleccionada[0], cartasSeleccionada[1], cartasSeleccionada[2]});
        jugador2.getManoDeCartas().quitarCartas(new Carta[]{cartasSeleccionada[3], cartasSeleccionada[4], cartasSeleccionada[5]});
        jugador3.getManoDeCartas().quitarCartas(new Carta[]{cartasSeleccionada[6], cartasSeleccionada[7], cartasSeleccionada[8]});
        jugador4.getManoDeCartas().quitarCartas(new Carta[]{cartasSeleccionada[9], cartasSeleccionada[10], cartasSeleccionada[11]});

        Jugador jugadorAlQueDarCartas1 = null;
        Jugador jugadorAlQueDarCartas2 = null;
        Jugador jugadorAlQueDarCartas3 = null;
        Jugador jugadorAlQueDarCartas4 = null;

        switch (turnoActual) {
            case 1:
                jugadorAlQueDarCartas1 = jugador1.getJugadorALaIzquierda();
                jugadorAlQueDarCartas2 = jugador2.getJugadorALaIzquierda();
                jugadorAlQueDarCartas3 = jugador3.getJugadorALaIzquierda();
                jugadorAlQueDarCartas4 = jugador4.getJugadorALaIzquierda();
                break;
            case 2:
                jugadorAlQueDarCartas1 = jugador1.getJugadorALaDerecha();
                jugadorAlQueDarCartas2 = jugador2.getJugadorALaDerecha();
                jugadorAlQueDarCartas3 = jugador3.getJugadorALaDerecha();
                jugadorAlQueDarCartas4 = jugador4.getJugadorALaDerecha();
                break;
            case 3:
                jugadorAlQueDarCartas1 = jugador1.getJugadorAlFrente();
                jugadorAlQueDarCartas2 = jugador2.getJugadorAlFrente();
                jugadorAlQueDarCartas3 = jugador3.getJugadorAlFrente();
                jugadorAlQueDarCartas4 = jugador4.getJugadorAlFrente();
                break;
        }
        jugadorAlQueDarCartas1.getManoDeCartas().agregarCartas(new Carta[]{cartasSeleccionada[0], cartasSeleccionada[1], cartasSeleccionada[2]});
        jugadorAlQueDarCartas2.getManoDeCartas().agregarCartas(new Carta[]{cartasSeleccionada[3], cartasSeleccionada[4], cartasSeleccionada[5]});
        jugadorAlQueDarCartas3.getManoDeCartas().agregarCartas(new Carta[]{cartasSeleccionada[6], cartasSeleccionada[7], cartasSeleccionada[8]});
        jugadorAlQueDarCartas4.getManoDeCartas().agregarCartas(new Carta[]{cartasSeleccionada[9], cartasSeleccionada[10], cartasSeleccionada[11]});

        ++turnoActual;
        if (turnoActual > 3) {
            turnoActual = 1;
        }
        cartaActual = 0;
        
        decidirTurnoDelJugador();//quitar 
    }

    public DatosRealizarTurnoJugadorActual realizarTurnoDeJugadorActual(Carta c) {
        DatosRealizarTurnoJugadorActual d = new DatosRealizarTurnoJugadorActual();
        if (cartaActual < 13) {
            Jugador jugadorActual = getJugadorActual();

            jugadorActual.getManoDeCartas().quitarCartas(new Carta[]{c});
            mesa.agregarCarta(jugadorActual, c);
            if (mesa.hay4Cartas()) {
                d.seRecojieronLasCartasPqFueLaCuarta = true;

                Jugador jugadorQueSeLlevaLasCartas = mesa.getJugadorDeMayorCarta();
                d.jugadorQueSellevoLosPuntos = jugadorQueSeLlevaLasCartas;
                int puntosEnLaMesa = mesa.getCantidadDePuntos();
                d.cantidadDePuntosQueSeLlevo = puntosEnLaMesa;

                jugadorQueSeLlevaLasCartas.agregarPuntos(puntosEnLaMesa);
                mesa = new Mesa();
                //cartaActual = cartaActual++ % 13;
                cartaActual++;
                if (jugadorQueSeLlevaLasCartas.getPuntos() >= 100) {
                    juegoTerminado = true;
                }

            }
            turnoDeJugador = ++turnoDeJugador % 4;
            //System.out.println("turnoDeJugador="+turnoDeJugador);
        }
        return d;

    }

    public boolean hayQuePasarElTurno() {
        return cartaActual == 13;
    }

    public Jugador getJugadorActual() {
        Jugador jugadorActual = null;
        switch (turnoDeJugador) {
            case 0:
                jugadorActual = jugador1;
                break;
            case 1:
                jugadorActual = jugador2;
                break;
            case 2:
                jugadorActual = jugador3;
                break;
            case 3:
                jugadorActual = jugador4;
                break;

        }
        return jugadorActual;
    }

    public TablaPosiciones getTablaPosiciones() {
        if (juegoTerminado) {
            return new TablaPosiciones(new Jugador[]{jugador1, jugador2, jugador3, jugador4});
        }
        return null;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public int getTurnoDeJugador() {
        return turnoDeJugador;
    }

    public int getCartaActual() {
        return cartaActual;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public Jugador getJugador3() {
        return jugador3;
    }

    public Jugador getJugador4() {
        return jugador4;
    }

    public Mesa getMesa() {
        return mesa;
    }

}

    
    static class DatosDePasoDeTurno {

    public Carta cartasSeleccionadasJugador1[] = new Carta[3];
    public Carta cartasSeleccionadasJugador2[] = new Carta[3];
    public Carta cartasSeleccionadasJugador3[] = new Carta[3];
    public Carta cartasSeleccionadasJugador4[] = new Carta[3];
    public int jugadorQueLeTocaSeleccionarCartas;//1-4

    public JuegoDecorazones j;

    public DatosDePasoDeTurno(JuegoDecorazones j) {
        resetear(j);
    }

    public void resetear(JuegoDecorazones j) {
        this.j = j;
        jugadorQueLeTocaSeleccionarCartas = 0;
    }

    public boolean enProcesoDePasarTurno() {
        return (!j.isJuegoTerminado()) && (j.hayQuePasarElTurno());
    }

    public Jugador getJugadorQueLetocaSeleccionar() {
        switch (jugadorQueLeTocaSeleccionarCartas) {
            case 0:
                return j.getJugador1();
            case 1:
                return j.getJugador2();
            case 2:
                return j.getJugador3();
            case 3:
                return j.getJugador4();
        }
        return null;
    }

    public boolean elJugadorTieneLaCarta(Carta c) {
        Jugador jugadorActual = getJugadorQueLetocaSeleccionar();
        return jugadorActual.getManoDeCartas().tieneCartasAll(new Carta[]{c});
    }

    public boolean addCarta(int indice, Carta c) {
        Carta[] cartasCorrespondientes = getMatrisCorrespondiente();
        for (int i = 0; i < indice; i++) {
            if (cartasCorrespondientes[i].esIguala(c)) {
                return false;
            }
        }
        cartasCorrespondientes[indice] = c;
        return true;
    }

    private Carta[] getMatrisCorrespondiente() {
        switch (jugadorQueLeTocaSeleccionarCartas) {
            case 0:
                return cartasSeleccionadasJugador1;
            case 1:
                return cartasSeleccionadasJugador2;
            case 2:
                return cartasSeleccionadasJugador3;
            case 3:
                return cartasSeleccionadasJugador4;
        }
        return null;
    }

    public void pasarAlSiguienJugador() {
        jugadorQueLeTocaSeleccionarCartas = ++jugadorQueLeTocaSeleccionarCartas % 4;
        //System.out.println("siguiente=" + jugadorQueLeTocaSeleccionarCartas);
    }

    public boolean esElUltimoJugador() {
        return jugadorQueLeTocaSeleccionarCartas == 3;
    }

    public Carta[] getTodasLasCartasSeleccionadas() {
        Carta[] cartasParaSeleccionadasParaPasarElTurno = new Carta[12];
        int count = 0;
        for (int k = 0; k < 3; k++) {
            cartasParaSeleccionadasParaPasarElTurno[count] = cartasSeleccionadasJugador1[k];
            count++;
        }
        for (int k = 0; k < 3; k++) {
            cartasParaSeleccionadasParaPasarElTurno[count] = cartasSeleccionadasJugador2[k];
            count++;
        }
        for (int k = 0; k < 3; k++) {
            cartasParaSeleccionadasParaPasarElTurno[count] = cartasSeleccionadasJugador3[k];
            count++;
        }
        for (int k = 0; k < 3; k++) {
            cartasParaSeleccionadasParaPasarElTurno[count] = cartasSeleccionadasJugador4[k];
            count++;
        }
        return cartasParaSeleccionadasParaPasarElTurno;
    }
}
static class MensajesAConsola {

    public JuegoDecorazones j;
    public DatosDePasoDeTurno dpt;

    public MensajesAConsola(JuegoDecorazones j, DatosDePasoDeTurno dpt) {
        this.j = j;
        this.dpt = dpt;
    }

    public String getStrDeCarta(Carta c) {
        if (c == null) {
            return "null";
        }
        return c.getPalo().getNombreDelPalo() + "-" + c.getNumero().getNombreDelNumero();
    }

    public void delPrincipio() {
        if (j.isJuegoTerminado()) {
            //System.out.println("El juego ya termino por favor escriba \"inicar\"");
            System.out.println("Bienvenido al Juego Por Favor escriba  \"iniciar\"");
        } else {
            if (dpt.enProcesoDePasarTurno()) {
                Jugador j2 = dpt.getJugadorQueLetocaSeleccionar();

                System.out.println(j2.getNombre() + " seleccione las cartas a pasar");
            } else {
                System.out.println("Escoja la carta con \"jugar nombre carta\" " + j.getJugadorActual().getNombre());
            }
        }

    }

    public void alIniciarJuego() {
        System.out.println("Juego Comenzado");
        //System.out.println("Escoja la carta con \"jugar nombre carta\" " + j.getJugadorActual().getNombre());
    }

    public void mostrarCartas(Carta[] C) {
        String lineaAMostrar = "";
        for (int i = 0; i < C.length; i++) {
            if (i != 0) {
                lineaAMostrar += " , ";
            }
            Carta cartaActual = C[i];
            lineaAMostrar += getStrDeCarta(cartaActual);
        }
        System.out.println(lineaAMostrar);
    }

    public void mostrarPuntos() {
        String lineaAMostrar2 = "";
        lineaAMostrar2 += j.getJugador1().getNombre() + " " + j.getJugador1().getPuntos() + " ";
        lineaAMostrar2 += j.getJugador2().getNombre() + " " + j.getJugador2().getPuntos() + " ";
        lineaAMostrar2 += j.getJugador3().getNombre() + " " + j.getJugador3().getPuntos() + " ";
        lineaAMostrar2 += j.getJugador4().getNombre() + " " + j.getJugador4().getPuntos() + " ";
        System.out.println(lineaAMostrar2);
    }

    public void elJugadorNoTieneLaCarta(Carta c) {
        System.out.println("No tienes esta carta " + getStrDeCarta(c));
    }

    public void cartaEscritaIncorrecta() {
        System.out.println("Carta escrita de forma Incorrecta ");
    }

    public void sePasoElTurno() {
        System.out.println("Se paso el turno");
    }

    public void seRecojieronLasCartas(DatosRealizarTurnoJugadorActual d) {
        System.out.println("El " + d.jugadorQueSellevoLosPuntos.getNombre() + " se llevo " + d.cantidadDePuntosQueSeLlevo);
    }

    public void mostrarCartasEnJuego() {
        String lineaAMostrar = "Cartas en Juego En La Mesa ";
        for (int i = 0; i < j.getMesa().getCartasEnMesa().length; i++) {
            if (i != 0) {
                lineaAMostrar += " , ";
            }
            Carta cartaActual = j.getMesa().getCartasEnMesa()[i].getCarta();
            lineaAMostrar += getStrDeCarta(cartaActual);
        }
        System.out.println(lineaAMostrar);
    }

    public void mostarTablaDePoscionesEnFinDeJuego() {
        TablaPosiciones tp = j.getTablaPosiciones();

        String lineaAMostrar2 = "Juego Terminado:\n";
        lineaAMostrar2 += tp.getJugadores()[0].getNombre() + " " + tp.getJugadores()[0].getPuntos() + "\n";
        lineaAMostrar2 += tp.getJugadores()[1].getNombre() + " " + tp.getJugadores()[1].getPuntos() + "\n";
        lineaAMostrar2 += tp.getJugadores()[2].getNombre() + " " + tp.getJugadores()[2].getPuntos() + "\n";
        lineaAMostrar2 += tp.getJugadores()[3].getNombre() + " " + tp.getJugadores()[3].getPuntos() + "\n";
        System.out.println(lineaAMostrar2);
    }

    public void comandoIncorrecto(String linea) {
        System.out.println("\"" + linea + "\" no se reconoce como comando interno");
    }

    public void cartaRepetida(Carta c) {
        System.out.println("Carta repetida " + getStrDeCarta(c));
    }

    public void ayuda() {
        System.out.println("ayuda222");
    }

    public void ayuda(String comando) {
        switch (comando) {
            case "select":
                System.out.println("explicacion selc");
                return;
            case "a":
                System.out.println("explicacion selc");
                return;
            case "b":
                System.out.println("explicacion selc");
                return;
            case "c":
                System.out.println("explicacion selc");
                return;
        }
        System.out.println("no se reconoce este comando");
    }

}

 public static void juegoDeCorazonesPorConsola() {
        Scanner s = new Scanner(System.in);

        JuegoDecorazones j = new JuegoDecorazones();
        DatosDePasoDeTurno d = new DatosDePasoDeTurno(j);
        MensajesAConsola m = new MensajesAConsola(j, d);

        
//        j.comenzarJuego(new Jugador[]{new Jugador("Jugador1"), new Jugador("Jugador2"), new Jugador("Jugador3"), new Jugador("Jugador4")});
        While1:
        while (true) {
            //System.out.println("2d.jugadorQueLeTocaSeleccionarCartas"+d.jugadorQueLeTocaSeleccionarCartas);
            m.delPrincipio();

            String linea = s.nextLine();
            
            
//            j.pasarTurno(new Carta[]{j.getJugador1().getManoDeCartas().getCartas()[0]
//            ,j.getJugador1().getManoDeCartas().getCartas()[1]
//            ,j.getJugador1().getManoDeCartas().getCartas()[2]
//            ,j.getJugador2().getManoDeCartas().getCartas()[0]
//            ,j.getJugador2().getManoDeCartas().getCartas()[1]
//            ,j.getJugador2().getManoDeCartas().getCartas()[2]
//            ,j.getJugador3().getManoDeCartas().getCartas()[0]
//            ,j.getJugador3().getManoDeCartas().getCartas()[1]
//            ,j.getJugador3().getManoDeCartas().getCartas()[2]
//            ,j.getJugador4().getManoDeCartas().getCartas()[0]
//            ,j.getJugador4().getManoDeCartas().getCartas()[1]
//            ,j.getJugador4().getManoDeCartas().getCartas()[2]});
            
            switch (linea) {
                case "iniciar":
                case "reiniciar":

                    j.comenzarJuego(new Jugador[]{new Jugador("Jugador1"), new Jugador("Jugador2"), new Jugador("Jugador3"), new Jugador("Jugador4")});
                    d.resetear(j);
                    m.alIniciarJuego();
                    continue While1;
                case "ayuda":
                    m.ayuda();
                    continue While1;
                
                case "exit":
                    System.exit(0);
                    break;
            }
            if(linea.startsWith("ayuda ")){
                String comando = sub(linea, "ayuda ");
                m.ayuda(comando);
                continue While1;
            }
            
            if (!j.isJuegoTerminado()) {
                switch (linea) {

                    case "mostrar":
                        Jugador jugadorActual = j.getJugadorActual();
                        if (j.hayQuePasarElTurno()) {
                            jugadorActual = d.getJugadorQueLetocaSeleccionar();
//                            System.out.println("entro");
//                            System.out.println("jugadorActual="+jugadorActual.getNombre());
                        }

                        Carta[] cartasEnLaMano = jugadorActual.getManoDeCartas().getCartas();
                        m.mostrarCartas(cartasEnLaMano);
//                        Carta[] cartasEnLaMano = j.getJugador1().getManoDeCartas().getCartas();
//                        m.mostrarCartas(cartasEnLaMano);
//                        cartasEnLaMano = j.getJugador2().getManoDeCartas().getCartas();
//                        m.mostrarCartas(cartasEnLaMano);
//                        cartasEnLaMano = j.getJugador3().getManoDeCartas().getCartas();
//                        m.mostrarCartas(cartasEnLaMano);
//                        cartasEnLaMano = j.getJugador4().getManoDeCartas().getCartas();
//                        m.mostrarCartas(cartasEnLaMano);

                        continue While1;
                    case "mostrar puntos":
                        m.mostrarPuntos();
                        continue While1;

                }

                if (j.hayQuePasarElTurno()) {
                    //String subLinea = linea.substring("select ".length());
                    if (linea.startsWith("select ")) {
                        String subLinea = sub(linea, "select ");

                        for (int i = 0; i < 3; i++) {
                            Carta c = getCartaSeleccionada(subLinea);
                            //System.out.println("c=" + c);
                            //subLinea = subLinea.substring(m.getStrDeCarta(c).length());

                            if (c != null) {
                                if (i != 2) {
                                    subLinea = sub(subLinea, m.getStrDeCarta(c) + " ");
                                }
                                if (d.elJugadorTieneLaCarta(c)) {
                                    if (d.addCarta(i, c)) {
                                        if (i == 2) {
                                            if (d.esElUltimoJugador()) {
                                                j.pasarTurno(d.getTodasLasCartasSeleccionadas());
                                                m.sePasoElTurno();
                                            }
                                            d.pasarAlSiguienJugador();
                                            //System.out.println("d.jugadorQueLeTocaSeleccionarCartas"+d.jugadorQueLeTocaSeleccionarCartas);
                                            //break;
                                            continue While1;
                                        }

                                    } else {
                                        m.cartaRepetida(c);
                                        continue While1;
                                    }

                                } else {
                                    m.elJugadorNoTieneLaCarta(c);
                                    //break;
                                    continue While1;
                                }

                            } else {
                                m.cartaEscritaIncorrecta();
                                //break;
                                continue While1;
                            }

                        }
                    }

                } else {
                    //System.out.println("linea="+linea);
                    if (linea.startsWith("jugar ")) {
                        //String subLinea = linea.substring("jugar ".length());
                        String subLinea = sub(linea, "jugar ");
                        Carta c = getCartaSeleccionada(subLinea);
                        if (c != null) {
                            if (j.getJugadorActual().getManoDeCartas().tieneCartasAll(new Carta[]{c})) {
                                DatosRealizarTurnoJugadorActual datos = j.realizarTurnoDeJugadorActual(c);
                                if (datos.seRecojieronLasCartasPqFueLaCuarta) {
                                    m.seRecojieronLasCartas(datos);
                                } else {
                                    m.mostrarCartasEnJuego();
                                }
                                if (!j.isJuegoTerminado()) {
                                    if (j.hayQuePasarElTurno()) {
                                        m.sePasoElTurno();
                                        j.barajearYRepartir();
                                        continue While1;
                                    } else {
                                        //break;
                                        continue While1;
                                    }
                                } else {
                                    m.mostarTablaDePoscionesEnFinDeJuego();
                                    continue While1;
                                }

                            } else {
                                m.elJugadorNoTieneLaCarta(c);
                                continue While1;
                            }

                        } else {
                            m.cartaEscritaIncorrecta();
                            continue While1;
                            //System.out.println("El comando jugar tiene que ser de la forma \"jugar nombre carta\"");
                        }

                    }

                }

            }
            m.comandoIncorrecto(linea);
        }

    }

    public static Carta getCartaSeleccionada(String subLinea) {
        for (int i = 0; i < TipoDePalo.VALUES.length; i++) {
            if (subLinea.startsWith(TipoDePalo.VALUES[i].getNombreDelPalo())) {
                TipoDePalo t = TipoDePalo.VALUES[i];
                //System.out.println("t="+t.getNombreDelPalo() + "-");
                subLinea = sub(subLinea, t.getNombreDelPalo() + "-");//.replace("-", "");// + "-"
                for (int k = 0; k < TipoDeNumero.VALUES.length; k++) {
//                    System.out.println("subLinea="+subLinea+" "+TipoDeNumero.VALUES[k].getNombreDelNumero());
//                    if(TipoDeNumero.VALUES[k].getNombreDelNumero().equals("Q")){
//                        System.out.println("2subLinea="+subLinea);
//                    }
                    if (subLinea.startsWith(TipoDeNumero.VALUES[k].getNombreDelNumero())) {
                        Carta c = new Carta(t, TipoDeNumero.VALUES[k]);
                        return c;
                    }
                }

                break;
            }
        }
        return null;
    }

    public static String sub(String palabraCompleta, String trozo) {
        //return palabraCompleta.substring(trozo.length()-1);
//        System.out.println("palabraCompleta=" + palabraCompleta);
//        System.out.println("trozo=" + trozo);
//        System.out.println("trozo.length()=" + trozo.length());
//        System.out.println("palabraCompleta.length()=" + palabraCompleta.length());
        return palabraCompleta.substring(trozo.length());
    }

    
    
    public static void main(String[] args) {
        juegoDeCorazonesPorConsola();
    }

}
