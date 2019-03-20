1.改library 用于拍照和选择相册
2.在主APP AndroidManifest.xml声明2个Activity
    <activity android:name="com.dyc.piclibrary.activity.PickBigImagesActivity">
    </activity>
    <activity android:name="com.dyc.piclibrary.activity.PickOrTakeImageActivity">
    </activity>
3.调用相册
  Intent intent=new Intent(context,PickOrTakeImageActivity.class);
  intent.putExtra(PickOrTakeImageActivity.EXTRA_NUMS,4);//图片的最大张数  不设置默认4张
  intent.putExtra(PickOrTakeImageActivity.IS_CROPS,true);//是否调用裁剪
  startActivityForResult(intent, REQUEST_CODE_SELECT_PICS);

4.回掉数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_PICS:
                    ArrayList<String> tempPaths= (ArrayList<String>) data.getSerializableExtra("data");//图片数据
                    break;
            }
        }
    }
5. 单独调用拍照
 　AlbumUtils．takePicture(Activity activity)
６.单独调用裁剪
　　AlbumUtils．crop(Uri uri,Activity activity)

