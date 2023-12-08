import neznayka.*;

import java.lang.Character;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Book book = new Book("Книжка", new Coordinate(3.4, 2.1), "том, что за наружной Луной есть какие-то огромные планеты и звезды, на которых тоже якобы живут коротышки.");
        Sink sink = new Sink("Раковина", new Coordinate(4.2, 2.6));

        Clothes trousers = new Clothes("trousers");
        Clothes tshirt = new Clothes("t-shirt");

        ArrayList NeznaykaClothes = new ArrayList<Clothes>();
        NeznaykaClothes.add(trousers);
        NeznaykaClothes.add(tshirt);

        ArrayList firstShortyClothes = new ArrayList<Clothes>();
        firstShortyClothes.add(trousers);
        firstShortyClothes.add(tshirt);

        ArrayList secondShortyClothes = new ArrayList<Clothes>();
        secondShortyClothes.add(trousers);
        secondShortyClothes.add(tshirt);

        ArrayList thirdShortyClothes = new ArrayList<Clothes>();
        secondShortyClothes.add(trousers);
        thirdShortyClothes.add(tshirt);

        Shorty neznayka = new Shorty(new Coordinate(0, 0), "Незнайка", MentalState.SANE, NeznaykaClothes);
        Shorty firstShorty = new Shorty(new Coordinate(3.5, 4.5), "Коротышка 1", MentalState.SANE, firstShortyClothes);
        Shorty secondShorty = new Shorty(new Coordinate(3.8, 4.3), "Коротышка 2", MentalState.SANE, secondShortyClothes);
        Shorty thirdShorty = new Shorty(new Coordinate(3.8, 4.1), "Коротышка 3", MentalState.SANE, thirdShortyClothes);

        ArrayList shortiesInArgument = new ArrayList<Character>();
        shortiesInArgument.add(firstShorty);
        shortiesInArgument.add(secondShorty);
        shortiesInArgument.add(thirdShorty);

        String output = "";

        Argument shortiesArgument = new Argument(shortiesInArgument);

        neznayka.move(sink.getCoordinates()[0], sink.getCoordinates()[1]);
        output += sink.interacted(neznayka) + "\n";
        output += shortiesArgument.start() + "\n";
        output += firstShorty.talk(neznayka.toString() + " рассказывает разные небылицы") + "\n";
        output += neznayka.act("сбивает полицию с толку.") + "\n";

        shortiesArgument.setLeader(firstShorty);
        output += shortiesArgument.getLeader().toString() + " - лидер спора." + "\n";

        output += thirdShorty.toString() + " стало жарко и он снял одежду." + "\n";
        thirdShortyClothes.clear();

        neznayka.setMental(MentalState.INSANE);
        output += secondShorty.talk(neznayka.toString() + " попросту дурачок, он " + neznayka.getMental()) + "\n";

        shortiesArgument.setLeader(secondShorty);
        output += shortiesArgument.getLeader().toString() + " - лидер спора." + "\n";

        output += thirdShorty.think(neznayka.toString() + " , должно быть " + neznayka.getMental()) + "\n";
        neznayka.move(book.getCoordinates()[0], book.getCoordinates()[1]);
        output += book.interacted(neznayka) + "\n";
        output += book.toString() + "\n";
        output += neznayka.think("наверно, я прилетел к нам с такой планеты") + "\n";

        shortiesArgument.setLeader(thirdShorty);
        output += shortiesArgument.getLeader().toString() + " - лидер спора." + "\n";

        output += shortiesArgument.finish() + "\n";

        System.out.print(output);
    }
}