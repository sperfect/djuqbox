package gr.sperfect.djuqbox.webapp.shared.data;


public class YoutubeSong extends Song implements IHasThumbnail {

	

	@Override
	public String getUri() {
		// TODO Auto-generated method stub
		String _url = super.getUri();
		if (_url != null && !_url.startsWith("http://..."))
		{
			_url = "http://.." + _url;
		}
		return _url;
	}
	

}
