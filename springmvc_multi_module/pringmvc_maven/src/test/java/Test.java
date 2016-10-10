import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by 45595 on 2016/9/9.
 */
public class Test {
    public static void main(String [] args){
        String aa= "aa,bb,cc";
        List<String> list = splitSimpleString(aa,',');
        //Map<String, String> map = new HashMap<>();
        Set<String> s = new HashSet<>();
        System.out.println(list.size());

    }

    static public List<String> splitSimpleString(String source, char gap)
    {
        List<String> result = new LinkedList<String>();
        if (source == null) return result;
        char[] sourceChars = source.toCharArray();
        int startIndex = 0, index = -1;
        while (index++ != sourceChars.length)
        {
            if (index == sourceChars.length || sourceChars[index] == gap)
            {
                char[] section = new char[index - startIndex];
                System.arraycopy(sourceChars, startIndex,
                        section, 0, index - startIndex);
                result.add(String.valueOf(section));
                startIndex = index + 1;
            }
        }
        return result;
    }
}
