package and.fast.simple;

import android.graphics.Color;

import java.util.Arrays;
import java.util.List;

import and.fast.simple.entities.ImageEntity;

public class DataStore {

    public static List<ImageEntity> sImageList = Arrays.asList(
            // 2010-04-01
            new ImageEntity(1270087788000L, Color.DKGRAY),
            new ImageEntity(1270087788000L, Color.DKGRAY),

            // 2019-04-11
            new ImageEntity(1554959626000L, Color.GRAY),
            new ImageEntity(1554959626000L, Color.GRAY),

            // 2020-01-01
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),

            // 2018-06-11
            new ImageEntity(1528694026000L, Color.DKGRAY),
            new ImageEntity(1528694026000L, Color.DKGRAY),
            new ImageEntity(1528694026000L, Color.DKGRAY),
            new ImageEntity(1528694026000L, Color.DKGRAY),

            // 2020-05-20
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),

            // 2020-08-20
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY)
    );
}
