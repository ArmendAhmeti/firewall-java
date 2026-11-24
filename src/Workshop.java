void main() {
    // 1. Porti
    int porti = Integer.parseInt(IO.readln("Porti: ").strip());
    if (porti < 0 || porti > 65535) {
        IO.println("Porti nuk ekziston.");
        return;
    }

    // 2. Protokolli (shkronja te medha) dhe rreziku me switch
    String protokolli = IO.readln("Protokolli (TCP/UDP): ").strip().toUpperCase();

    double rreziku = switch (porti) {
        case 22 -> 7.5;
        case 53 -> 3.5;
        case 80 -> 4.0;
        case 443 -> 2.0;
        case 3389 -> 9.0;
        default -> 5.0;
    };

    boolean lejohet = false;

    if (protokolli.equals("TCP")) {
        // 3. TCP
        if (porti == 80 || porti == 443) {
            lejohet = true;
        } else if (porti == 22) {
            String pergjigja = IO.readln("Nga rrjeti i brendshëm? (po/jo): ").strip().toLowerCase();
            lejohet = pergjigja.equals("po");
        }
    } else if (protokolli.equals("UDP")) {
        // 4. UDP
        lejohet = (porti == 53);
    } else {
        IO.println("Protokoll i panjohur.");
    }

    // 5. Ternary + printf
    String vendimi = lejohet ? "LEJOHET" : "BLLOKOHET";
    System.out.printf("%s %d: %s%n", protokolli, porti, vendimi);
    System.out.printf(Locale.US, "Rreziku i portit: %.2f nga 10%n", rreziku);
}

