package videogen;

import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.xtext.example.mydsl.VideoGenStandaloneSetupGenerated;
import org.xtext.example.mydsl.playlist.Playlist;
import org.xtext.example.mydsl.playlist.PlaylistFactory;
import org.xtext.example.mydsl.playlist.video;
import org.xtext.example.mydsl.videoGen.AlternativeVideoSeq;
import org.xtext.example.mydsl.videoGen.MandatoryVideoSeq;
import org.xtext.example.mydsl.videoGen.OptionalVideoSeq;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoSeq;
import videogen.Operations;

@SuppressWarnings("all")
public class FFmpegRep {
  public static VideoGeneratorModel loadVideoGenerator(final URI uri) {
    VideoGeneratorModel _xblockexpression = null;
    {
      VideoGenStandaloneSetupGenerated _videoGenStandaloneSetupGenerated = new VideoGenStandaloneSetupGenerated();
      _videoGenStandaloneSetupGenerated.createInjectorAndDoEMFRegistration();
      ResourceSetImpl _resourceSetImpl = new ResourceSetImpl();
      Resource res = _resourceSetImpl.getResource(uri, true);
      EList<EObject> _contents = res.getContents();
      EObject _get = _contents.get(0);
      _xblockexpression = ((VideoGeneratorModel) _get);
    }
    return _xblockexpression;
  }
  
  public void saveVideoGenerator(final URI uri, final VideoGeneratorModel pollS) {
    try {
      ResourceSetImpl _resourceSetImpl = new ResourceSetImpl();
      Resource rs = _resourceSetImpl.createResource(uri);
      EList<EObject> _contents = rs.getContents();
      _contents.add(pollS);
      HashMap<Object, Object> _hashMap = new HashMap<Object, Object>();
      rs.save(_hashMap);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static Playlist videoToPlaylist(final VideoGeneratorModel videoGen) {
    Playlist _xblockexpression = null;
    {
      String data = "";
      String _data = data;
      data = (_data + "#this is a comment\n");
      Playlist playList = PlaylistFactory.eINSTANCE.createPlaylist();
      EList<VideoSeq> _videoseqs = videoGen.getVideoseqs();
      for (final VideoSeq videoseq : _videoseqs) {
        if ((videoseq instanceof MandatoryVideoSeq)) {
          video vid = PlaylistFactory.eINSTANCE.createvideo();
          VideoDescription _description = ((MandatoryVideoSeq) videoseq).getDescription();
          String _location = _description.getLocation();
          vid.setPath(_location);
          VideoDescription _description_1 = ((MandatoryVideoSeq) videoseq).getDescription();
          int _duration = _description_1.getDuration();
          vid.setDuration(_duration);
          VideoDescription _description_2 = ((MandatoryVideoSeq) videoseq).getDescription();
          String _description_3 = _description_2.getDescription();
          vid.setDescription(_description_3);
          EList<video> _videos = playList.getVideos();
          _videos.add(vid);
        } else {
          if ((videoseq instanceof OptionalVideoSeq)) {
            final VideoDescription desc = ((OptionalVideoSeq) videoseq).getDescription();
            String _location_1 = desc.getLocation();
            boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_location_1);
            boolean _not = (!_isNullOrEmpty);
            if (_not) {
              Random _random = new Random();
              int _nextInt = _random.nextInt(2);
              boolean _equals = (_nextInt == 1);
              if (_equals) {
                video vid_1 = PlaylistFactory.eINSTANCE.createvideo();
                String _location_2 = desc.getLocation();
                vid_1.setPath(_location_2);
                int _duration_1 = desc.getDuration();
                vid_1.setDuration(_duration_1);
                String _description_4 = desc.getDescription();
                vid_1.setDescription(_description_4);
                EList<video> _videos_1 = playList.getVideos();
                _videos_1.add(vid_1);
              }
            }
          } else {
            final AlternativeVideoSeq altvid = ((AlternativeVideoSeq) videoseq);
            String _videoid = altvid.getVideoid();
            boolean _isNullOrEmpty_1 = StringExtensions.isNullOrEmpty(_videoid);
            boolean _not_1 = (!_isNullOrEmpty_1);
            if (_not_1) {
              EList<VideoDescription> _videodescs = altvid.getVideodescs();
              Random _random_1 = new Random();
              EList<VideoDescription> _videodescs_1 = altvid.getVideodescs();
              int _size = _videodescs_1.size();
              int _nextInt_1 = _random_1.nextInt(_size);
              VideoDescription seq = _videodescs.get(_nextInt_1);
              video vid_2 = PlaylistFactory.eINSTANCE.createvideo();
              String _location_3 = seq.getLocation();
              vid_2.setPath(_location_3);
              String _description_5 = seq.getDescription();
              vid_2.setDescription(_description_5);
              int _duration_2 = seq.getDuration();
              vid_2.setDuration(_duration_2);
              EList<video> _videos_2 = playList.getVideos();
              _videos_2.add(vid_2);
            }
          }
        }
      }
      _xblockexpression = playList;
    }
    return _xblockexpression;
  }
  
  public static String playlistToFFMPEG(final Playlist playlist) {
    String _xblockexpression = null;
    {
      final StringBuffer content = new StringBuffer();
      EList<video> _videos = playlist.getVideos();
      final Consumer<video> _function = (video video) -> {
        String _path = video.getPath();
        String _plus = ("file \'" + _path);
        String _plus_1 = (_plus + "\'\n");
        content.append(_plus_1);
      };
      _videos.forEach(_function);
      String _string = content.toString();
      Operations.writeToFile("ffmpeg", _string);
      InputOutput.<StringBuffer>println(content);
      _xblockexpression = content.toString();
    }
    return _xblockexpression;
  }
  
  public static void generateThumbnail(final VideoGeneratorModel videoGen) {
    EList<VideoSeq> _videoseqs = videoGen.getVideoseqs();
    for (final VideoSeq videoseq : _videoseqs) {
      if ((videoseq instanceof MandatoryVideoSeq)) {
        final VideoDescription desc = ((MandatoryVideoSeq) videoseq).getDescription();
        String _videoid = desc.getVideoid();
        boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_videoid);
        boolean _not = (!_isNullOrEmpty);
        if (_not) {
          String _location = desc.getLocation();
          String _videoid_1 = desc.getVideoid();
          Operations.execCommandFFmpeg(_location, _videoid_1);
        }
      } else {
        if ((videoseq instanceof OptionalVideoSeq)) {
          final VideoDescription desc_1 = ((OptionalVideoSeq) videoseq).getDescription();
          String _videoid_2 = desc_1.getVideoid();
          boolean _isNullOrEmpty_1 = StringExtensions.isNullOrEmpty(_videoid_2);
          boolean _not_1 = (!_isNullOrEmpty_1);
          if (_not_1) {
            String _location_1 = desc_1.getLocation();
            String _videoid_3 = desc_1.getVideoid();
            Operations.execCommandFFmpeg(_location_1, _videoid_3);
          }
        } else {
          final AlternativeVideoSeq altvid = ((AlternativeVideoSeq) videoseq);
          String _videoid_4 = altvid.getVideoid();
          boolean _isNullOrEmpty_2 = StringExtensions.isNullOrEmpty(_videoid_4);
          boolean _not_2 = (!_isNullOrEmpty_2);
          if (_not_2) {
            EList<VideoDescription> _videodescs = altvid.getVideodescs();
            for (final VideoDescription vdesc : _videodescs) {
              String _videoid_5 = vdesc.getVideoid();
              boolean _isNullOrEmpty_3 = StringExtensions.isNullOrEmpty(_videoid_5);
              boolean _not_3 = (!_isNullOrEmpty_3);
              if (_not_3) {
                String _location_2 = vdesc.getLocation();
                String _videoid_6 = vdesc.getVideoid();
                Operations.execCommandFFmpeg(_location_2, _videoid_6);
              }
            }
          }
        }
      }
    }
  }
  
  public static void main(final String[] args) {
    URI _createURI = URI.createURI("videos.videogen");
    VideoGeneratorModel videoGen = FFmpegRep.loadVideoGenerator(_createURI);
    FFmpegRep.generateThumbnail(videoGen);
  }
}