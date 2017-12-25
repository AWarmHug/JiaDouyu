package com.warm.playerlib.just;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.warm.playerlib.DisplayUtils;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 作者：warm
 * 时间：2017-12-18 11:00
 * 描述：
 */

public class JustVideoPlayer extends FrameLayout implements BasePlayController.PlayControl {
    private static final String TAG = "JustVideoView";
    private IMediaPlayer mMediaPlayer;//ijkPlayer
    private JustTextureView mTextureView;
    private FrameLayout mContainer;
    //判断是否自动旋转
    private boolean autoRotation;
    //用于监听屏幕的旋转情况
    private OrientationEventListener mOrientationEventListener;

    private BasePlayController mController;

    private SurfaceTexture mSurfaceTexture;

    private String urlPath;

    /**
     * 判断是否手动暂停
     */
    private boolean userPause;

    /**
     * 初始状态
     */
    public static final int STATE_IDLE = 0;

    /**
     * 出错
     */
    public static final int STATE_ERROR = -1;
    /**
     * 没有网络
     */
    public static final int STATE_NET_ERROR = -2;

    /**
     * 视频缓冲中
     */
    public static final int STATE_BUFFERING_START = 1;

    /**
     * 视频缓冲结束
     */
    public static final int STATE_BUFFERING_END = 2;


    /**
     * 准备中的状态
     */
    public static final int STATE_PREPARING = 3;

    /**
     * 准备好的状态
     */
    public static final int STATE_PREPARED = 4;
    /**
     * 播放ing
     */
    public static final int STATE_PLAYING = 5;

    /**
     * 暂停
     */
    public static final int STATE_PAUSE = 6;

    /**
     * 播放完成
     */
    public static final int STATE_COMPLETION = 8;


    private int mState = STATE_IDLE;


    public static final int SCALE_16_9 = 100;
    public static final int SCALE_4_3 = 101;
    public static final int SCALE_MATCH_PARENT = 102;
    public static final int SCALE_WRAP_CONTENT = 103;

    private int mScale = SCALE_MATCH_PARENT;

    //竖向
    public static final int SCREEN_ORIENTATION_PORTRAIT = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    //横向
    public static final int SCREEN_ORIENTATION_LANDSCAPE = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    //反向横向
    public static final int SCREEN_ORIENTATION_REVERSE_LANDSCAPE = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;

    //屏幕方向
    private int mScreenOrientation = SCREEN_ORIENTATION_PORTRAIT;


    public JustVideoPlayer(Context context) {
        this(context, null);
    }

    public JustVideoPlayer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustVideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContainer = new FrameLayout(context);
        mContainer.setBackgroundColor(Color.BLACK);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mContainer, params);
    }


    private void initPlayer() {
        if (mMediaPlayer == null) {
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
        if (mTextureView != null) {
            removeView(mTextureView);
        }
        mSurfaceTexture = null;
        mTextureView = new JustTextureView(getContext());
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                if (mSurfaceTexture != null) {
                    mTextureView.setSurfaceTexture(mSurfaceTexture);
                } else {
                    mSurfaceTexture = surface;
                    mMediaPlayer.setSurface(new Surface(surface));
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Log.d(TAG, "onSurfaceTextureDestroyed: ");
                return mSurfaceTexture == null;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {


            }
        });
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        mContainer.addView(mTextureView, 0, params);
    }


    @Override
    public boolean isPlaying() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        } else {
            return false;
        }
    }

    @Override
    public void start() {
        if (mState == STATE_IDLE) {
            initPlayer();
            setSourceAndPrepareAndStart();
        } else {
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
                mState = STATE_PLAYING;
                setControllerState();
            }
        }
    }


    private void setSourceAndPrepareAndStart() {

        try {
            mMediaPlayer.setDataSource(urlPath);
            mMediaPlayer.prepareAsync();
            mState = STATE_PREPARING;
            setControllerState();
        } catch (IOException e) {
            e.printStackTrace();
            mState = STATE_ERROR;
            setControllerState();
        }
    }

    @Override
    public void pauseUser() {
        pause();
        userPause = true;
    }

    public void pause() {
        if (mMediaPlayer != null && isPlaying() && mState != STATE_PAUSE) {
            mMediaPlayer.pause();
            mState = STATE_PAUSE;
            setControllerState();
            userPause = false;
        }
    }


    public void resume() {
        if (!isPlaying() && mState == STATE_PAUSE && !userPause) {
            start();
        }
    }


    public void resetPlayer() {
        if (mMediaPlayer != null)
            mMediaPlayer.reset();
        initPlayer();
    }


    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        if (autoRotation && mOrientationEventListener != null) {
            mOrientationEventListener.disable();
        }
        removeView(mTextureView);
    }

    @Override
    public void seekTo(long seekTo) {
        Log.d(TAG, "seekTo: " + seekTo + "d=" + mMediaPlayer.getDuration());
        mMediaPlayer.seekTo(seekTo);

    }

    @Override
    public boolean isFull() {
        return mScreenOrientation != SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    public void toFull() {
        setScreenOrientation(SCREEN_ORIENTATION_LANDSCAPE);
        _toFull();
    }

    private void _toFull() {
        DisplayUtils.hideBar((Activity) getContext());
        //全屏时，需要把播放器容器中的内容放到Activity的content中
        this.removeView(mContainer);
        Activity activity = ((Activity) getContext());
        ViewGroup frame = (ViewGroup) activity.findViewById(android.R.id.content);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frame.addView(mContainer, params);
        mController.setPlayerState(BasePlayController.STATE_FULL);
    }

    @Override
    public void toNotFull() {
        setScreenOrientation(SCREEN_ORIENTATION_PORTRAIT);
        _toNotFull();
    }

    private void _toNotFull() {
        DisplayUtils.showBar((Activity) getContext());
        Activity activity = ((Activity) getContext());
        ViewGroup frame = (ViewGroup) activity.findViewById(android.R.id.content);
        frame.removeView(mContainer);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(mContainer, params);
        mController.setPlayerState(BasePlayController.STATE_NO_FULL);
    }

    public void setScreenOrientation(int screenOrientation) {
        this.mScreenOrientation = screenOrientation;
        ((Activity) getContext()).setRequestedOrientation(screenOrientation);
    }

    @Override
    public long getCurrentPosition() {
        return mMediaPlayer == null ? 0 : mMediaPlayer.getCurrentPosition();
    }

    @Override
    public long getDuration() {

        return mMediaPlayer == null ? 0 : mMediaPlayer.getDuration();
    }

    public void setScaleType(int scaleType) {
        this.mScale = scaleType;
        if (mTextureView != null) {
            mTextureView.setScaleType(mScale);
        }
    }

    @Override
    public void startAgain() {
        if (mState == STATE_COMPLETION) {
            resetPlayer();
            setSourceAndPrepareAndStart();
        }
    }

    private void setControllerState() {
        if (mController != null)
            mController.setPlayState(mState);
    }

    public boolean onBackPressed() {
        if (mController.getPlayerState() == BasePlayController.STATE_FULL) {
            //设置为正常
            toNotFull();
            return true;
        } else {
            return false;
        }
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
            mController.setBuffering(percent);

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
            mState = STATE_ERROR;
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
            switch (what) {
                case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                    mState = STATE_BUFFERING_START;
                    setControllerState();
                    break;
                case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    mState = STATE_BUFFERING_END;
                    setControllerState();
                    break;
            }


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
            start();

        }
    };


    private IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int width, int height,
                                       int sar_num, int sar_den) {
            //获取到宽高，默认宽高比例应该在这里来计算。
            mTextureView.setSize(width, height);
            mTextureView.setScaleType(mScale);
            Log.d(TAG, String.format("onVideoSizeChanged: width=%s,height=%s,sar_num=%s,sar_den%s", width, height, sar_num, sar_den));
        }
    };

    /**
     * 完成拖动后加载后的回调
     */
    private IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {

        }
    };


    public JustVideoPlayer setDataSource(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    public JustVideoPlayer setAutoRotation() {
        this.autoRotation = true;
        mOrientationEventListener = new OrientationEventListener(getContext()) {

            @Override
            public void onOrientationChanged(int orientation) {
                if (((orientation >= 0) && (orientation < 45)) || (orientation > 315)) {
                    //设置竖屏
                    Log.d(TAG, "设置竖屏");
                    //ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    if (mScreenOrientation != SCREEN_ORIENTATION_PORTRAIT) {
                        setScreenOrientation(SCREEN_ORIENTATION_PORTRAIT);
                        _toNotFull();
                    }

                } else if (orientation > 225 && orientation < 315) {
                    //设置横屏
                    Log.d(TAG, "设置横屏");
                    //ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    if (mScreenOrientation != SCREEN_ORIENTATION_LANDSCAPE) {
                        setScreenOrientation(SCREEN_ORIENTATION_LANDSCAPE);
                        _toFull();
                    }

                } else if (orientation > 45 && orientation < 135) {
                    // 设置反向横屏
                    Log.d(TAG, "反向横屏");
//                    ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                    if (mScreenOrientation != SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                        setScreenOrientation(SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                        _toFull();
                    }

                } else if (orientation > 135 && orientation < 225) {
                    // 设置反向横屏
                    Log.d(TAG, "反向竖屏");
                }
            }

        };
        return this;
    }


    public void addController(BasePlayController playController) {
        removeView(mController);
        playController.setControl(this);
        this.mController = playController;
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContainer.addView(mController, params);
        if (autoRotation && mOrientationEventListener != null && mOrientationEventListener.canDetectOrientation()) {
            mOrientationEventListener.enable();
        }
    }


}
