package title;

import java.util.regex.Pattern;

public class LineSearch {
	
	//根据状态过滤
	public static boolean entryNeedToShow(LineEntry entry) {

		if (TitlePanel.rdbtnCheckedItems.isSelected()&& entry.statusIsChecked()) {
			return true;
		}

		if (TitlePanel.rdbtnCheckingItems.isSelected()&& entry.getCheckStatus().equals(LineEntry.CheckStatus_Checking)) {
			return true;//小心 == 和 equals的区别，之前这里使用 ==就导致了checking状态的条目的消失。
		}

		if (TitlePanel.rdbtnUnCheckedItems.isSelected()&& entry.getCheckStatus().equals(LineEntry.CheckStatus_UnChecked)) {
			return true;
		}

		return false;
	}
	
	public static boolean textFilte(LineEntry line,String keyword) {
		if (keyword.length() == 0) {
			return true;
		}else {//全局搜索
			if (new String(line.getRequest()).toLowerCase().contains(keyword)) {
				return true;
			}
			if (new String(line.getResponse()).toLowerCase().contains(keyword)) {
				return true;
			}
			if (new String(line.getUrl()).toLowerCase().contains(keyword)) {
				return true;
			}
			if (new String(line.getIP()).toLowerCase().contains(keyword)) {
				return true;
			}
			if (new String(line.getCDN()).toLowerCase().contains(keyword)) {
				return true;
			}
			if (new String(line.getComment()).toLowerCase().contains(keyword)) {
				return true;
			}
			return false;
		}
	}

	//支持部分类似google dork的搜索语法
	//Host url header body request response comment
	public static boolean dorkFilte(LineEntry line,String dork,String keyword) {
		
		if (keyword.length() == 0) {
			return true;
		}
		
		//BurpExtender.getStdout().println(dork+":"+SearchDork.HOST.toString());
		if (dork.equalsIgnoreCase(SearchDork.HOST.toString())) {
			if (line.getHost().toLowerCase().contains(keyword)) {
				return true;
			}else {
				return false;
			}
		}

		if (dork.equalsIgnoreCase(SearchDork.URL.toString())) {
			if (line.getUrl().toLowerCase().contains(keyword)) {
				return true;
			}else {
				return false;
			}
		}

		if (dork.equalsIgnoreCase(SearchDork.REQUEST.toString())) {
			if (new String(line.getRequest()).toLowerCase().contains(keyword)) {
				return true;
			}else {
				return false;
			}
		}

		if (dork.equalsIgnoreCase(SearchDork.RESPONSE.toString())) {
			if (new String(line.getResponse()).toLowerCase().contains(keyword)) {
				return true;
			}else {
				return false;
			}
		}

		if (dork.equalsIgnoreCase(SearchDork.COMMENT.toString())) {
			if (line.getComment().toLowerCase().contains(keyword)) {
				return true;
			}else {
				return false;
			}
		}
		
		if (dork.equalsIgnoreCase(SearchDork.REGEX.toString())) {
			return regexFilte(line,keyword);
		}
		return false;
	}
	
	public static boolean regexFilte(LineEntry line,String regex) {
		Pattern pRegex = Pattern.compile(regex);

		if (regex.trim().length() == 0) {
			return true;
		} else {
			if (pRegex.matcher(new String(line.getRequest()).toLowerCase()).find()) {
				return true;
			}
			if (pRegex.matcher(new String(line.getResponse()).toLowerCase()).find()) {
				return true;
			}
			if (pRegex.matcher(new String(line.getUrl()).toLowerCase()).find()) {
				return true;
			}
			if (pRegex.matcher(new String(line.getIP()).toLowerCase()).find()) {
				return true;
			}
			if (pRegex.matcher(new String(line.getCDN()).toLowerCase()).find()) {
				return true;
			}
			if (pRegex.matcher(new String(line.getComment()).toLowerCase()).find()) {
				return true;
			}
			return false;
		}
	}
	
}
