package com.yicha.app.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

public class SeachViewPagerAdapter extends FragmentPagerAdapter
{
  private Context context;
  private List<Fragment> list;
  private String[] titles;

  public SeachViewPagerAdapter(FragmentManager paramFragmentManager, Context paramContext, List<Fragment> paramList, String[] paramArrayOfString)
  {
    super(paramFragmentManager);
    this.context = paramContext;
    this.list = paramList;
    this.titles = paramArrayOfString;
    Log.i("TAG", "qwe" + paramArrayOfString.length);
  }

  public int getCount()
  {
    return this.list.size();
  }

  public Fragment getItem(int paramInt)
  {
    return (Fragment)this.list.get(paramInt);
  }

  public CharSequence getPageTitle(int paramInt)
  {
    return this.titles[paramInt];
  }
}

/* Location:           C:\Users\liuli\Desktop\APP上线材料\反编译工具包\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.oa.chuangxiang.cxoa_app.Adapter.SeachViewPagerAdapter
 * JD-Core Version:    0.6.2
 */