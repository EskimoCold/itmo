import neznayka.*;
import neznayka.Character;
import neznayka.enums.MentalState;
import neznayka.enums.Planet;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Clothes trousers = new Clothes("trousers");
        Clothes tshirt = new Clothes("t-shirt");

        ArrayList<Clothes> NeznaykaClothes = new ArrayList<>();
        NeznaykaClothes.add(trousers);
        NeznaykaClothes.add(tshirt);

        ArrayList<Clothes> firstShortyClothes = new ArrayList<>();
        firstShortyClothes.add(trousers);
        firstShortyClothes.add(tshirt);

        ArrayList<Clothes> secondShortyClothes = new ArrayList<>();
        secondShortyClothes.add(trousers);
        secondShortyClothes.add(tshirt);

        ArrayList<Clothes> thirdShortyClothes = new ArrayList<>();
        secondShortyClothes.add(trousers);
        thirdShortyClothes.add(tshirt);

        Coordinate neznaykaCords = new Coordinate(0, 0);
        Coordinate firstShortyCords = new Coordinate(3.5, 4.5);
        Coordinate secondShortyCords = new Coordinate(3.8, 4.3);
        Coordinate thirdShortyCords = new Coordinate(3.8, 4.1);
        Coordinate bookCords = new Coordinate(3.4, 2.1);
        Coordinate sinkCords = new Coordinate(4.2, 2.6);

        Shorty neznayka = new Shorty(neznaykaCords, new RadiusCoordinateArea(2, neznaykaCords), "Незнайка", MentalState.SANE, Planet.EARTH, NeznaykaClothes);
        Shorty firstShorty = new Shorty(firstShortyCords, new RadiusCoordinateArea(2, firstShortyCords), "Коротышка 1", MentalState.SANE, Planet.EARTH, firstShortyClothes);
        Shorty secondShorty = new Shorty(secondShortyCords, new RadiusCoordinateArea(2, secondShortyCords), "Коротышка 2", MentalState.SANE, Planet.EARTH, secondShortyClothes);
        Shorty thirdShorty = new Shorty(thirdShortyCords, new RadiusCoordinateArea(2, thirdShortyCords), "Коротышка 3", MentalState.SANE, Planet.EARTH, thirdShortyClothes);

        Book book = new Book("Книжка", bookCords, new RadiusCoordinateArea(1, bookCords), "том, что за наружной Луной есть какие-то огромные планеты и звезды, на которых тоже якобы живут коротышки.");
        Sink sink = new Sink("Раковина", sinkCords, new RadiusCoordinateArea(1, sinkCords));

        ArrayList<Character> shortiesInArgument = new ArrayList<>();
        shortiesInArgument.add(firstShorty);
        shortiesInArgument.add(secondShorty);
        shortiesInArgument.add(thirdShorty);

        String output = "";

        ArgumentManager shortiesArgument = new ArgumentManager(shortiesInArgument);

        neznayka.move(4, 3);
        output += sink.interacted(neznayka) + "\n";
        output += shortiesArgument.start() + "\n";

        output += firstShorty.talk(neznayka + " рассказывает разные небылицы") + "\n";
        Argument firstArg = new Argument(neznayka + " рассказывает разные небылицы");
        shortiesArgument.addArgument(firstShorty, firstArg);

        output += neznayka.act("сбивает полицию с толку.") + "\n";

        output += shortiesArgument.getLeader()[0] + " - лидер спора, сила его агрументов - " + shortiesArgument.getLeader()[1] + "\n";

        output += thirdShorty + " стало жарко и он снял одежду." + "\n";
        thirdShortyClothes.clear();

        neznayka.setMental(MentalState.INSANE);
        output += secondShorty.talk(neznayka + " попросту дурачок, он " + neznayka.getMental()) + "\n";

        Argument secondArg = new Argument(neznayka + " попросту дурачок, он " + neznayka.getMental());
        shortiesArgument.addArgument(secondShorty, secondArg);

        output += shortiesArgument.getLeader()[0] + " - лидер спора, сила его агрументов - " + shortiesArgument.getLeader()[1] + "\n";

        output += thirdShorty.think(neznayka + " , должно быть " + neznayka.getMental()) + "\n";
        neznayka.move(book.getCoordinates()[0], book.getCoordinates()[1]);
        output += book.interacted(neznayka) + "\n";
        output += book + "\n";
        output += neznayka.think("наверно, я прилетел к нам с такой планеты") + "\n";
        neznayka.setPlanet(Planet.MARS);

        if (!neznayka.getPlanet().equals(thirdShorty.getPlanet())) {
            thirdShorty.rateOther(neznayka, "Он с другой планеты!");
        }

        output += thirdShorty + " оценил " + neznayka + ": " + neznayka.getRated().get(thirdShorty) + "\n";

        Argument thirdArg = new Argument("Он с другой планеты!");
        shortiesArgument.addArgument(thirdShorty, thirdArg);

        output += shortiesArgument.getLeader()[0] + " - лидер спора, сила его агрументов - " + shortiesArgument.getLeader()[1] + "\n";

        output += shortiesArgument.finish();

        System.out.print(output);
    }
}

// todo: radius(done), equals(done), helios(done), arguments(done), new uml