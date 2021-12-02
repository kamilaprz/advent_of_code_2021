import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

class Scratch {
    public static void main(String[] args) {
        var position = new Object() {
            int horizontal = 0;
            int depth = 0;
            int aim = 0;
        };
        Consumer<Integer> moveForward = steps -> {
            position.horizontal = position.horizontal + steps;
            position.depth = position.depth + (position.aim * steps);
        };
        Consumer<Integer> moveBackward = steps -> {
            position.horizontal = position.horizontal - steps;
        };
        Consumer<Integer> moveDown = steps -> {
            position.aim = position.aim + steps;
        };
        Consumer<Integer> moveUp = steps -> {
            position.aim = position.aim - steps;
        };

        Map<String, Consumer<Integer>> instructionDefinitions = Map.of(
                "forward", moveForward,
                "backward", moveBackward,
                "up", moveUp,
                "down", moveDown
        );

        String ins_test = "forward 5\n"
                + "down 5\n"
                + "forward 8\n"
                + "up 3\n"
                + "down 8\n"
                + "forward 2";

        Arrays.stream(ins_test.split("\n"))
                .map(text -> {
                    String[] s = text.split(" ");
                    return new Instruction(s[0], s[1]);
                })
                .forEach(instruction -> instructionDefinitions.get(instruction.definition).accept(instruction.steps));

        System.out.println(position.depth * position.horizontal);
    }

    static class Instruction {
        String definition;
        int steps;

        public Instruction(String def, String step) {
            definition = def;
            steps = Integer.parseInt(step);
        }
    }
}