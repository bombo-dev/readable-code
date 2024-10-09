package cleancode.studycafe.tobe2;

import cleancode.studycafe.tobe2.io.ConsoleInputHandler;
import cleancode.studycafe.tobe2.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe2.io.file.StudyCafeLockerPassFileReader;
import cleancode.studycafe.tobe2.io.file.StudyCafePassFileReader;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                new ConsoleInputHandler(),
                new ConsoleOutputHandler(),
                new StudyCafePassFileReader(),
                new StudyCafeLockerPassFileReader());
        studyCafePassMachine.run();
    }

}
