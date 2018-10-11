import jbook.util.Input;

public class RefactorString {
    public static void main(String[] args) {
        String s = Input.readString("Inserisci una stringa\n> ");
        char c = Input.readChar("Inserisci il carattere da sostituire\n> ");


        //System.out.println(s.replace(c,'?');
        System.out.println(replaceWhithQuestionMark(s, c));
    }

    private static String replaceWhithQuestionMark(String s, char c) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i< s.length(); i++)
            result.append(s.charAt(i) == c ? '?':s.charAt(i));
        return result.toString();
    }
}
