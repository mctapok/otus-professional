package Iterator;

import java.util.List;
import java.util.NoSuchElementException;

public final class Box implements IterableClass<String> {
    private final List<String> listOne = List.of("1", "2", "3");
    private final List<String> listTwo = List.of("2.1", "2.2");
    private final List<String> listThree = List.of("3.1");
    private final List<String> listFour = List.of("4.1", "4.2", "4,3");

    @Override
    public MyIterator<String> createIterator() {
        return new BoxIterator();
    }

    private class BoxIterator implements MyIterator<String> {
        private int listIndex;
        private int itemIndex;

        public BoxIterator() {
            this.listIndex = 1;
            this.itemIndex = 0;
        }

        @Override
        public boolean hasNext() {
            while (listIndex <= 4) {
                List<String> list = getList(listIndex);
                if (itemIndex < list.size()) {
                    return true;
                }
                listIndex++;
                itemIndex = 0;
            }
            return false;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no element");
            }
            List<String> list = getList(listIndex);
            return list.get(itemIndex++);
        }

        private List<String> getList(int listIndex) {
            switch (listIndex) {
                case 1:
                    return listOne;
                case 2:
                    return listTwo;
                case 3:
                    return listThree;
                case 4:
                    return listFour;
                default:
                    throw new IllegalStateException("no list exception");
            }
        }

    }
}
