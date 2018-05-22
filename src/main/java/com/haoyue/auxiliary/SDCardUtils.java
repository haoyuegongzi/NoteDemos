package com.haoyue.auxiliary;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Android设备相关工具类
 * @author chen1
 */
public class SDCardUtils {
	private static SDCardUtils deviceUtils = null;

	public static SDCardUtils getInstance() {
		if (deviceUtils == null) {
			deviceUtils = new SDCardUtils();
		}
		return deviceUtils;
	}

	/**
	 * 检查SD卡是否已经准备就绪
	 * @return true:准备就绪;  false:没有准备就绪;
	 */
	public static boolean isSDCardReady() {
		if (Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState())){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @return SD卡剩余空间，单位MB
	 */
	public static long getSDCardFreeSize() {
		File file = Environment.getExternalStorageDirectory();
		StatFs sf = new StatFs(file.getPath());
		long blockSize = sf.getBlockSize();
		long freeBlockNumber = sf.getAvailableBlocks();
		return blockSize * freeBlockNumber / 1024 / 1024;
	}

	/**
	 * @return SD卡整体空间大小，单位MB
	 */
	public static long getSDCardTotalSize() {
		// 取得SD卡文件路径
		File file = Environment.getExternalStorageDirectory();
		StatFs sf = new StatFs(file.getPath());
		// 获取单个数据块的大小(Byte)
		long blockSize = sf.getBlockSize();
		// 获取所有数据块数
		long allBlocks = sf.getBlockCount();
		// 返回SD卡大小,单位MB
		return (allBlocks * blockSize) / 1024 / 1024;
	}
}
