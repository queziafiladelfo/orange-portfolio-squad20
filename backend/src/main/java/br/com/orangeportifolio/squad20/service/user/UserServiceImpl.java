package br.com.orangeportifolio.squad20.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.orangeportifolio.squad20.dao.IUsersDAO;
import br.com.orangeportifolio.squad20.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUsersDAO dao;

	@Override
	public User create(@Valid User user) {
		if(dao.findByEmail(user.getEmail()) == null) {
			return dao.save(user);
		}
		System.err.println("Usuário já existe no banco de dados!");
		return null;
	}

	@Override
	public User findById(@NotNull Integer id) {
		return dao.findById(id).orElse(null);
	}
}