package com.onlinelibrary.dao;

import java.util.Set;

import com.onlinelibrary.model.Genre;

public interface GenreDao extends GenericDao<Genre, Long> {
	public Set<Genre> getAllGenres(); 
}
