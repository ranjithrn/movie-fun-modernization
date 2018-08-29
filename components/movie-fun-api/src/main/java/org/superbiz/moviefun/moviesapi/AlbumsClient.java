package org.superbiz.moviefun.moviesapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {
    private String albumUrl;
    private RestOperations restOperations;

    public AlbumsClient(String albumUrl, RestOperations restOperations) {
        this.albumUrl = albumUrl;
        this.restOperations = restOperations;
    }

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public void addAlbum(AlbumInfo albumInfo){
        restOperations.postForObject(albumUrl,albumInfo,AlbumInfo.class);
    }
    public List<AlbumInfo> getAlbums(){

        return restOperations.exchange(albumUrl, GET, null, albumListType).getBody();
    }
    public AlbumInfo find(long id){
        return restOperations.getForEntity(albumUrl+"/"+id, AlbumInfo.class).getBody();
    }

}
