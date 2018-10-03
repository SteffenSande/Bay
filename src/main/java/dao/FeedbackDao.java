package dao;

import entities.Feedback;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class FeedbackDao extends AbstractDao<Feedback, Integer> {

    public FeedbackDao() {
        super(Feedback.class);
    }

    @Override
    public List<Feedback> getAll() {
        return this.em.createQuery("SELECT item FROM Feedback item", tClass).getResultList();
    }
}
