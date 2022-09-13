package capers;

import jdk.jshell.execution.Util;

import java.io.File;
import java.io.IOException;

import static capers.Utils.*;
import static capers.Dog.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(CWD, ".capers"); // TODO Hint: look at the `join`
                                            //      function in Utils
    static final File story = Utils.join(CAPERS_FOLDER, "story.txt");
    static final File dogs = Utils.join(CAPERS_FOLDER, "dogs");
    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() throws IOException {
        // Create .capers directory
        if (!CAPERS_FOLDER.exists()) {
            CAPERS_FOLDER.mkdirs();
        }
        // Create story file if it does not exist.
        if (!story.exists()) {
            story.createNewFile();
        }
        // Create dogs directory if it does not exist
        if (!dogs.exists()) {
            dogs.mkdirs();
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        String curStory = Utils.readContentsAsString(story);
        String newStory;
        if (curStory.isEmpty()) {
            newStory = text;
        } else {
            newStory = curStory + "\n" + text;
        }
        Utils.writeContents(story, newStory);
        System.out.println(newStory);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog curDog = new Dog(name, breed, age);
        curDog.saveDog();
        System.out.println(curDog);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog curDog = Dog.fromFile(name);
        curDog.haveBirthday();
        curDog.saveDog();
    }
}
