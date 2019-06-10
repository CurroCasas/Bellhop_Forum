package forum.service;

public class ReplyPaginate {
	private int pivot;
	private int replybefore;
	private int replyafter;

	public int getPivot() {
		return pivot;
	}

	public void setPivot(int id) {
		this.pivot = pivot;
	}

	public int getReplybefore() {
		return replybefore;
	}

	public void setReplybefore(int replybefore) {
		this.replybefore = replybefore;
	}

	public int getReplyafter() {
		return replyafter;
	}

	public void setReplyafter(int replyafter) {
		this.replyafter = replyafter;
	}

}
