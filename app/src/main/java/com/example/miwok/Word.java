package com.example.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslaton;

    private final int NO_IMAGE=-1;
    private int mResourceId=NO_IMAGE;
    private int mAudioResourceId;

    public Word(String defaultTranslation,String miwokTranslation,int audioResourceId)
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslaton=miwokTranslation;
        mAudioResourceId=audioResourceId;
    }
    public Word(String defaultTranslation,int resourceId,String miwokTranslation , int audioResourceId )
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslaton=miwokTranslation;
        mResourceId=resourceId;
        mAudioResourceId=audioResourceId;
    }

    public String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }

    public int getmAudioResourceId(){  return mAudioResourceId; }

    public String getMiwokTranslation(){ return mMiwokTranslaton; }

    public int getmResourceId() {
        return mResourceId;
    }

    public boolean hasResourceid()
        {
            return  mResourceId!=NO_IMAGE;
        }

}
