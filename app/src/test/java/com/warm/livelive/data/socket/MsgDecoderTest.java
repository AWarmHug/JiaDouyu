package com.warm.livelive.data.socket;

import android.text.TextUtils;

import com.warm.livelive.douyu.data.socket.nativesocek.MsgDecoder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 作者：warm
 * 时间：2017-12-28 12:57
 * 描述：
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MsgDecoder.class, TextUtils.class})
public class MsgDecoderTest {


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void decode() throws Exception {

    }

    @Test
    public void decode1() throws Exception {

    }

    @Test
    public void appearNum() throws Exception {

    }

    @Test
    public void splie() throws Exception {
        PowerMockito.mockStatic(MsgDecoder.class);
        PowerMockito.when(MsgDecoder.ss("")).thenReturn(true);
        System.out.println(MsgDecoder.ss(""));

        PowerMockito.mockStatic(TextUtils.class);

        System.out.println(TextUtils.isEmpty(null));




    }

    @Test
    public void _decode() throws Exception {

    }

}