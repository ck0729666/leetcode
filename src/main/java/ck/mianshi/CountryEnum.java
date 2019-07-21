package ck.mianshi;

import lombok.Getter;

public enum CountryEnum {
    ONE(1, "QI"),TWO(2, "CHU"),THREE(3, "YAN"),FOUR(4, "ZHAO");

    @Getter private int index;
    @Getter private String name;

    CountryEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] myArray = CountryEnum.values();
        for(CountryEnum element : myArray) {
            if(index == element.getIndex()) {
                return element;
            }
        }
        return null;
    }

}
