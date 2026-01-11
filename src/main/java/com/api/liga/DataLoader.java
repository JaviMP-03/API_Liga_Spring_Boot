/**
 *      IGNORA ESTA CLASE, SON PRUEBAS PARA CREAR EQUIPOS EN LOCAL, LO DEJO POR SI ES UTIL PARA MOSTRAR EL PROCESO DEL CODIGO
 */

//package com.api.liga;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.api.liga.model.Equipo;
//import com.api.liga.model.Jugador;
//import com.api.liga.model.Posicion;
//import com.api.liga.service.EquipoService;
//import com.api.liga.service.JugadorService;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final EquipoService equipoService;
//    private final JugadorService jugadorService;
//
//    public DataLoader(EquipoService equipoService, JugadorService jugadorService) {
//        this.equipoService = equipoService;
//        this.jugadorService = jugadorService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // ==== EQUIPOS ====
//        Equipo celta = new Equipo("Celta", LocalDate.of(1923, 8, 23), "Balaídos", 24791, "Benítez");
//        Equipo real = new Equipo("Real Madrid", LocalDate.of(1902, 3, 6), "Santiago Bernabéu", 81044, "Ancelotti");
//        Equipo barca = new Equipo("Barcelona", LocalDate.of(1899, 11, 29), "Camp Nou", 99354, "Xavi");
//        Equipo sevilla = new Equipo("Sevilla", LocalDate.of(1890, 1, 25), "Ramón Sánchez Pizjuán", 43000, "Lopetegui");
//        Equipo atletico = new Equipo("Atlético de Madrid", LocalDate.of(1903, 4, 26), "Wanda Metropolitano", 68000, "Simeone");
//
//        equipoService.crearEquipo(celta);
//        equipoService.crearEquipo(real);
//        equipoService.crearEquipo(barca);
//        equipoService.crearEquipo(sevilla);
//        equipoService.crearEquipo(atletico);
//
//        // ==== JUGADORES ====
//        // CELTA
//        jugadorService.crearJugador(new Jugador("Iago Aspas", LocalDate.of(1987,8,1), 10, new BigDecimal("1500000"), Posicion.DELANTERO, celta));
//        jugadorService.crearJugador(new Jugador("Fran Beltrán", LocalDate.of(1999,3,9), 8, new BigDecimal("700000"), Posicion.MEDIOCENTRO, celta));
//        jugadorService.crearJugador(new Jugador("Augusto Solari", LocalDate.of(1992,1,1), 7, new BigDecimal("500000"), Posicion.DELANTERO, celta));
//        jugadorService.crearJugador(new Jugador("Néstor Araujo", LocalDate.of(1991,8,29), 4, new BigDecimal("600000"), Posicion.DEFENSA, celta));
//        jugadorService.crearJugador(new Jugador("Rubén Blanco", LocalDate.of(1995,7,27), 1, new BigDecimal("400000"), Posicion.PORTERO, celta));
//
//        // REAL MADRID
//        jugadorService.crearJugador(new Jugador("Cristiano Ronaldo", LocalDate.of(1985,2,5), 7, new BigDecimal("2000000"), Posicion.DELANTERO, real));
//        jugadorService.crearJugador(new Jugador("Karim Benzema", LocalDate.of(1987,12,19), 9, new BigDecimal("1800000"), Posicion.DELANTERO, real));
//        jugadorService.crearJugador(new Jugador("Luka Modric", LocalDate.of(1985,9,9), 10, new BigDecimal("1500000"), Posicion.MEDIOCENTRO, real));
//        jugadorService.crearJugador(new Jugador("Thibaut Courtois", LocalDate.of(1992,5,11), 1, new BigDecimal("1200000"), Posicion.PORTERO, real));
//        jugadorService.crearJugador(new Jugador("Sergio Ramos", LocalDate.of(1986,3,30), 4, new BigDecimal("1300000"), Posicion.DEFENSA, real));
//
//        // BARCELONA
//        jugadorService.crearJugador(new Jugador("Leo Messi", LocalDate.of(1987,6,24), 10, new BigDecimal("2500000"), Posicion.DELANTERO, barca));
//        jugadorService.crearJugador(new Jugador("Pedri", LocalDate.of(2002,11,25), 8, new BigDecimal("900000"), Posicion.MEDIOCENTRO, barca));
//        jugadorService.crearJugador(new Jugador("Jordi Alba", LocalDate.of(1989,3,21), 18, new BigDecimal("800000"), Posicion.DEFENSA, barca));
//        jugadorService.crearJugador(new Jugador("Marc-André ter Stegen", LocalDate.of(1992,4,30), 1, new BigDecimal("1100000"), Posicion.PORTERO, barca));
//        jugadorService.crearJugador(new Jugador("Ansu Fati", LocalDate.of(2002,10,31), 22, new BigDecimal("700000"), Posicion.DELANTERO, barca));
//
//        // SEVILLA
//        jugadorService.crearJugador(new Jugador("Rakitic", LocalDate.of(1988,3,10), 10, new BigDecimal("1000000"), Posicion.MEDIOCENTRO, sevilla));
//        jugadorService.crearJugador(new Jugador("En-Nesyri", LocalDate.of(1997,6,1), 9, new BigDecimal("950000"), Posicion.DELANTERO, sevilla));
//        jugadorService.crearJugador(new Jugador("Jesús Navas", LocalDate.of(1985,11,21), 7, new BigDecimal("650000"), Posicion.DEFENSA, sevilla));
//        jugadorService.crearJugador(new Jugador("Bono", LocalDate.of(1990,10,14), 1, new BigDecimal("500000"), Posicion.PORTERO, sevilla));
//        jugadorService.crearJugador(new Jugador("Fernando", LocalDate.of(1985,7,9), 6, new BigDecimal("600000"), Posicion.MEDIOCENTRO, sevilla));
//
//        // ATLÉTICO DE MADRID
//        jugadorService.crearJugador(new Jugador("Griezmann", LocalDate.of(1991,3,21), 7, new BigDecimal("1200000"), Posicion.DELANTERO, atletico));
//        jugadorService.crearJugador(new Jugador("Koke", LocalDate.of(1992,1,8), 6, new BigDecimal("900000"), Posicion.MEDIOCENTRO, atletico));
//        jugadorService.crearJugador(new Jugador("Jan Oblak", LocalDate.of(1993,1,7), 1, new BigDecimal("1300000"), Posicion.PORTERO, atletico));
//        jugadorService.crearJugador(new Jugador("Felipe", LocalDate.of(1989,1,12), 4, new BigDecimal("800000"), Posicion.DEFENSA, atletico));
//        jugadorService.crearJugador(new Jugador("João Félix", LocalDate.of(1999,11,10), 10, new BigDecimal("1100000"), Posicion.DELANTERO, atletico));
//
//        System.out.println("✅ Datos iniciales cargados: 5 equipos, 25 jugadores");
//    }
//}
