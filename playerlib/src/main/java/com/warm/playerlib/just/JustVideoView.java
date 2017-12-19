package com.warm.playerlib.just;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 作者：warm
 * 时间：2017-12-18 11:00
 * 描述：
 */

public class JustVideoView extends FrameLayout implements BasePlayController.PlayControl {
    private static final String TAG = "JustVideoView";
    private IMediaPlayer mMediaPlayer;//ijkPlayer
    private JustTextureView mTextureView;

    private BasePlayController mController;

    private String urlPath;

    /**
     * 初始状态
     */
    public static final int STATE_INIT = 0;

    /**
     * 出错
     */
    public static final int STATE_ERROR = -1;
    /**
     * 没有网络
     */
    public static final int STATE_NET_ERROR = -2;

    /**
     * 加载中
     */
    public static final int STATE_LONGING = 1;

    /**
     * 准备好的状态
     */
    public static final int STATE_PREPAREING = 2;

    /**
     * 准备好的状态
     */
    public static final int STATE_PREPARED = 2;
    /**
     * 播放ing
     */
    public static final int STATE_PLAYING = 3;

    /**
     * 播放暂停
     */
    public static final int STATE_PAUSE = 4;

    /**
     * 播放完成
     */
    private static final int STATE_COMPLETION = 5;


    private int mState = STATE_INIT;


    public static final int SCALE_16_9 = 100;
    public static final int SCALE_4_3 = 101;
    public static final int SCALE_MATCH_PARENT = 102;
    public static final int SCALE_WRAP_CONTENT = 103;

    private int mScale = SCALE_WRAP_CONTENT;


    public JustVideoView(Context context) {
        this(context, null);
    }

    public JustVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void addController(BasePlayController playController) {
        this.mController = playController;
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(playController, params);
        mController.setControl(this);
    }


    private void initPlayer() {
        if (mMediaPlayer==null) {
            mMediaPlayer = new IjkMediaPlayer();
            //开启硬解码
            ((IjkMediaPlayer) mMediaPlayer).setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
            ((IjkMediaPlayer) mMediaPlayer).setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
            ((IjkMediaPlayer) mMediaPlayer).setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
            mMediaPlayer.setOnErrorListener(onErrorListener);
            mMediaPlayer.setOnSeekCompleteListener(onSeekCompleteListener);
            mMediaPlayer.setOnInfoListener(onInfoListener);
            mMediaPlayer.setOnPreparedListener(onPreparedListener);
            mMediaPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
            mMediaPlayer.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
        }
        addTextureView();

    }

    private void addTextureView() {
        if (mTextureView!=null){
            mController.removeView(mTextureView);
        }

        mTextureView = new JustTextureView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Log.d(TAG, "onSurfaceTextureAvailable: ");
                mMediaPlayer.setSurface(new Surface(surface));
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        mController.addView(mTextureView, 0, params);
    }


    public void setDataSource(String urlPath) {
        this.urlPath = urlPath;

    }

    @Override
    public boolean isPlaying() {
        if (mMediaPlayer!=null) {
            return mMediaPlayer.isPlaying();
        }else {
            return false;
        }
    }

    @Override
    public void start() {
        if (mState == STATE_INIT) {
            initPlayer();
            setDataSourceAndPrepare();
        }
        mMediaPlayer.start();
        mState = STATE_PLAYING;
        setControllerState();
    }

    private void setDataSourceAndPrepare() {

        try {
            mMediaPlayer.setDataSource(urlPath);
            mMediaPlayer.prepareAsync();
            mState = STATE_PREPAREING;
            setControllerState();
        } catch (IOException e) {
            e.printStackTrace();
            mState = STATE_ERROR;
        }

    }

    @Override
    public void pause() {
        if (mMediaPlayer!=null) {
            mMediaPlayer.pause();
        }
        mState = STATE_PAUSE;
        setControllerState();
    }

    public void resume(){
        if (!isPlaying()&&mState==STATE_PAUSE){
            start();
        }

    }



    public void resetPlayer(){
        mMediaPlayer.reset();
        initPlayer();
    }


    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void seekTo(long seekTo) {
        Log.d(TAG, "seekTo: " + seekTo + "d=" + mMediaPlayer.getDuration());
        if (mState == STATE_PAUSE || mState == STATE_PLAYING) {
            mMediaPlayer.seekTo(seekTo);
        }
    }

    @Override
    public void toFull() {

    }

    @Override
    public void toNotFull() {

    }


    @Override
    public long getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    @Override
    public long getDuration() {
        return mMediaPlayer.getDuration();
    }

    public void setScaleType(int scaleType) {
        this.mScale = scaleType;
        mTextureView.setScaleType(mScale);
    }

    private void setControllerState(){
        if (mController!=null)
            mController.setPlayState(mState);
    }


    /**
     * 播放完成的回调
     */
    private IMediaPlayer.OnCompletionListener onCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            mState = STATE_COMPLETION;
            setControllerState();
        }
    };



    /**
     * 播放缓冲回调
     */
    private IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int percent) {
            Log.d(TAG, "onBufferingUpdate: " + percent);
            mController.setBuffering( percent);

        }
    };

    /**
     * 播放出错回调
     */
    private IMediaPlayer.OnErrorListener onErrorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int what, int extra) {
            //onError: i=-10000i1=0
            Log.d(TAG, "onError: i=" + what + "i1=" + extra);
            mState=STATE_ERROR;
            setControllerState();
            return false;
        }
    };
    /**
     * 播放视频信息回调
     */
    private IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int extra) {
//            int MEDIA_INFO_UNKNOWN = 1;
//            int MEDIA_INFO_STARTED_AS_NEXT = 2;
//            int MEDIA_INFO_VIDEO_RENDERING_START = 3;
//            int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
//            int MEDIA_INFO_BUFFERING_START = 701;
//            int MEDIA_INFO_BUFFERING_END = 702;
//            int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
//            int MEDIA_INFO_BAD_INTERLEAVING = 800;
//            int MEDIA_INFO_NOT_SEEKABLE = 801;
//            int MEDIA_INFO_METADATA_UPDATE = 802;
//            int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
//            int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
//            int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;
//            int MEDIA_INFO_VIDEO_ROTATION_CHANGED = 10001;
//            int MEDIA_INFO_AUDIO_RENDERING_START = 10002;
//            int MEDIA_INFO_AUDIO_DECODED_START = 10003;
//            int MEDIA_INFO_VIDEO_DECODED_START = 10004;
//            int MEDIA_INFO_OPEN_INPUT = 10005;
//            int MEDIA_INFO_FIND_STREAM_INFO = 10006;
//            int MEDIA_INFO_COMPONENT_OPEN = 10007;
//            int MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE = 10100;

            Log.d(TAG, "onInfo: what=" + what + "extra=" + extra + "name=" + iMediaPlayer.getMediaInfo());

            return false;
        }
    };
    /**
     * 播放准备完毕回调
     */
    private IMediaPlayer.OnPreparedListener onPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            Log.d(TAG, "onPrepared: ");
            mState = STATE_PREPARED;
            setControllerState();

        }
    };


    private IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int width, int height,
                                       int sar_num, int sar_den) {
            //获取到宽高，默认宽高比例应该在这里来计算。
            mTextureView.setSize(width, height);
            mTextureView.setScaleType(SCALE_MATCH_PARENT);
            Log.d(TAG, String.format("onVideoSizeChanged: width=%s,height=%s,sar_num=%s,sar_den%s", width, height, sar_num, sar_den));
        }
    };

    /**
     * 拖动后完成的回调
     */
    private IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {


        }
    };


}
